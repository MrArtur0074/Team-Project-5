package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // PostgreSQL, Redis и т.д.

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Technology() {}

    public Technology(String name, Project project) {
        this.name = name;
        this.project = project;
    }

    // Геттеры и сеттеры
}