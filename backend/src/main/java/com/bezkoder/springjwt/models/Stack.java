package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "stacks")
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Backend, Frontend, Fullstack

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Stack() {}

    public Stack(String type, Project project) {
        this.type = type;
        this.project = project;
    }

    // Геттеры и сеттеры
}