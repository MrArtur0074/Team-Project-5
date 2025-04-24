package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Project markAsCompleted(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();

        if (project.getTakenBy() == null) {
            throw new RuntimeException("Project was not taken");
        }

        project.setCompleted(true);
        project.setMustBeChecked(false);

        // Добавляем в completedProjects пользователя
        User user = project.getTakenBy();
        user.getCompletedProjects().add(project);
        userRepository.save(user); // ✅ важно сохранить изменения у пользователя

        return projectRepository.save(project);
    }
}