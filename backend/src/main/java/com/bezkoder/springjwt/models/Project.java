package com.bezkoder.springjwt.models;

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
    private int participants;
    private String githubLink;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private Stack stack;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Technology> technologies;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Requirement> requirements;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private Outcome outcome;

    public Project() {}

    public Project(String name, String description, int participants, String githubLink) {
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.githubLink = githubLink;
    }

    // Геттеры и сеттеры
}