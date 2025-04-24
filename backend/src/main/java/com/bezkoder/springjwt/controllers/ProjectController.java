package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Вы не авторизованы");
        }
        List<Project> availableProjects = projectService.getAvailableProjects();
        return ResponseEntity.ok(availableProjects);
    }

    @GetMapping("/taken")
    public ResponseEntity<?> getTakenProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        return ResponseEntity.ok(projectService.getProjectsTakenByUser(user));
    }

    @PostMapping("/{id}/take")
    public ResponseEntity<?> takeProject(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Project project = projectService.getProjectById(id).orElseThrow();
        if (project.isTaken()) {
            return ResponseEntity.badRequest().body("Project already taken");
        }
        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        return ResponseEntity.ok(projectService.takeProject(project, user));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<?> submitProject(@PathVariable Long id,
                                           @RequestBody String submissionLink,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Project project = projectService.getProjectById(id).orElseThrow();
        if (!project.getTakenBy().getId().equals(userDetails.getId())) {
            return ResponseEntity.status(403).body("Not your project");
        }
        return ResponseEntity.ok(projectService.submitProject(project, submissionLink));
    }

    @GetMapping("/company")
    public ResponseEntity<?> getProjectsByCompany(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User company = userRepository.findById(userDetails.getId()).orElseThrow();
        return ResponseEntity.ok(projectService.getProjectsCreatedByCompany(company));
    }

    @GetMapping("/company/to-check")
    public ResponseEntity<?> getProjectsToCheck(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User company = userRepository.findById(userDetails.getId()).orElseThrow();
        return ResponseEntity.ok(projectService.getProjectsToCheck(company));
    }

    @PostMapping("/company/complete/{id}")
    public ResponseEntity<?> completeProject(@PathVariable Long id,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Project project = projectService.getProjectById(id).orElseThrow();
        if (!project.getCreatedBy().getId().equals(userDetails.getId())) {
            return ResponseEntity.status(403).body("Not your project");
        }
        return ResponseEntity.ok(projectService.markAsCompleted(project));
    }
}