package com.bezkoder.springjwt.payload.response;

import com.bezkoder.springjwt.models.Project;

public class CompletedProjectResponse {
    private Long id;
    private String submissionLink;
    private String status;
    private Project project;

    public CompletedProjectResponse(Long id, String submissionLink, String status, Project project) {
        this.id = id;
        this.submissionLink = submissionLink;
        this.status = status;
        this.project = project;
    }

    // геттеры
    public Long getId() { return id; }
    public String getSubmissionLink() { return submissionLink; }
    public String getStatus() { return status; }
    public Project getProject() { return project; }
}