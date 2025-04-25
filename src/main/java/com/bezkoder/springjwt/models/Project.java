package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String githubLink;

    private boolean taken;

    private boolean completed;

    private boolean mustBeChecked;

    @Column(columnDefinition = "TEXT")
    private String submissionLink;

    @ManyToOne
    @JoinColumn(name = "taken_by_id")
    @JsonBackReference(value = "user-taken-projects")
    private User takenBy;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    @JsonBackReference(value = "user-created-projects")
    private User createdBy;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Technology> technologies;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Requirement> requirements;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Outcome> outcomes;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private Stack stack;

    public Project() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getGithubLink() { return githubLink; }

    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }

    public boolean isTaken() { return taken; }

    public void setTaken(boolean taken) { this.taken = taken; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public boolean isMustBeChecked() { return mustBeChecked; }

    public void setMustBeChecked(boolean mustBeChecked) { this.mustBeChecked = mustBeChecked; }

    public String getSubmissionLink() { return submissionLink; }

    public void setSubmissionLink(String submissionLink) { this.submissionLink = submissionLink; }

    public User getTakenBy() { return takenBy; }

    public void setTakenBy(User takenBy) { this.takenBy = takenBy; }

    public User getCreatedBy() { return createdBy; }

    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public List<Technology> getTechnologies() { return technologies; }

    public void setTechnologies(List<Technology> technologies) { this.technologies = technologies; }

    public List<Requirement> getRequirements() { return requirements; }

    public void setRequirements(List<Requirement> requirements) { this.requirements = requirements; }

    public List<Outcome> getOutcomes() { return outcomes; }

    public void setOutcomes(List<Outcome> outcomes) { this.outcomes = outcomes; }

    public Stack getStack() { return stack; }

    public void setStack(Stack stack) { this.stack = stack; }
}