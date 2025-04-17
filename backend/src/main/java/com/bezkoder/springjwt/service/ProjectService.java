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
}