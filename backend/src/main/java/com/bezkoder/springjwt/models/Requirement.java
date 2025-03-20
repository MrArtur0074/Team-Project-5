package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "requirements")
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requirement; // Требования к проекту

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Requirement() {}

    public Requirement(String requirement, Project project) {
        this.requirement = requirement;
        this.project = project;
    }

    // Геттеры и сеттеры
}