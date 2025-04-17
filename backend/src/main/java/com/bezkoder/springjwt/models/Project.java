package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int participants;
    private String githubLink;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Technology> technologies = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Requirement> requirements = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Outcome> outcomes = new ArrayList<>();

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Stack stack;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getParticipants() { return participants; }
    public void setParticipants(int participants) { this.participants = participants; }

    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }

    public List<Technology> getTechnologies() { return technologies; }
    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
        if (technologies != null) technologies.forEach(t -> t.setProject(this));
    }

    public List<Requirement> getRequirements() { return requirements; }
    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
        if (requirements != null) requirements.forEach(r -> r.setProject(this));
    }

    public List<Outcome> getOutcomes() { return outcomes; }
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        if (outcomes != null) outcomes.forEach(o -> o.setProject(this));
    }

    public Stack getStack() { return stack; }
    public void setStack(Stack stack) {
        this.stack = stack;
        if (stack != null) stack.setProject(this);
    }
}