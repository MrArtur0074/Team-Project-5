package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "outcomes")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result; // Что получит участник проекта

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Outcome() {}

    public Outcome(String result, Project project) {
        this.result = result;
        this.project = project;
    }

    // Геттеры и сеттеры
}