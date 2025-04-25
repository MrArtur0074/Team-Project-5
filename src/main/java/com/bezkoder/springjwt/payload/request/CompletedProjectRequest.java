package com.bezkoder.springjwt.payload.request;

public class CompletedProjectRequest {
    private Long userId;
    private Long projectId;
    private String submissionLink;
    private String status;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getSubmissionLink() { return submissionLink; }
    public void setSubmissionLink(String submissionLink) { this.submissionLink = submissionLink; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}