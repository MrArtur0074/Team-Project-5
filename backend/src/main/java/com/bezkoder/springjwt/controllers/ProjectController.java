package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectController {

    @Autowired private ProjectService projectService;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private UserRepository userRepository;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Вы не авторизованы");
        }
        return ResponseEntity.ok(projectService.getAvailableProjects());
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
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<?> markProjectAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.markAsCompleted(id));
    }

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project,
                                           @AuthenticationPrincipal UserDetailsImpl currentUser) {
        User user = new User();
        user.setId(currentUser.getId());

        project.setCreatedBy(user);
        project.setTaken(false);
        project.setCompleted(false);
        project.setMustBeChecked(false);

        if (project.getTechnologies() != null)
            project.getTechnologies().forEach(t -> t.setProject(project));

        if (project.getRequirements() != null)
            project.getRequirements().forEach(r -> r.setProject(project));

        if (project.getOutcomes() != null)
            project.getOutcomes().forEach(o -> o.setProject(project));

        if (project.getStack() != null)
            project.getStack().setProject(project);

        Project saved = projectRepository.save(project);
        return ResponseEntity.ok(saved);
    }
}