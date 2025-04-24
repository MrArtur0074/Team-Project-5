package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getAvailableProjects() {
        return projectRepository.findByTakenFalse();
    }

    public List<Project> getProjectsTakenByUser(User user) {
        return projectRepository.findByTakenBy(user);
    }

    public List<Project> getProjectsCreatedByCompany(User user) {
        return projectRepository.findByCreatedBy(user);
    }

    public List<Project> getProjectsToCheck(User user) {
        return projectRepository.findByCreatedByAndMustBeCheckedTrue(user);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        if (project.getTechnologies() != null) {
            project.getTechnologies().forEach(t -> t.setProject(project));
        }
        if (project.getRequirements() != null) {
            project.getRequirements().forEach(r -> r.setProject(project));
        }
        if (project.getOutcomes() != null) {
            project.getOutcomes().forEach(o -> o.setProject(project));
        }
        if (project.getStack() != null) {
            project.getStack().setProject(project);
        }
        return projectRepository.save(project);
    }

    public Project takeProject(Project project, User user) {
        project.setTaken(true);
        project.setTakenBy(user);
        return projectRepository.save(project);
    }

    public Project submitProject(Project project, String submissionLink) {
        project.setSubmissionLink(submissionLink);
        project.setMustBeChecked(true);
        return projectRepository.save(project);
    }

    public Project markAsCompleted(Project project) {
        project.setCompleted(true);
        project.setMustBeChecked(false);
        return projectRepository.save(project);
    }
}