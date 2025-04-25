package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "completed_projects")
public class CompletedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submissionLink;

    private String status;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubmissionLink() { return submissionLink; }
    public void setSubmissionLink(String submissionLink) { this.submissionLink = submissionLink; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}