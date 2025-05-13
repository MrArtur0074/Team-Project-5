package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "stacks")
public class Stack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;
    private String details;

    @OneToOne
    @JoinColumn(name = "project_id", unique = true)
    @JsonBackReference
    private Project project;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}