package com.bezkoder.springjwt.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String portfolioLink;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompletedProject> completedProjects;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getPortfolioLink() { return portfolioLink; }
    public void setPortfolioLink(String portfolioLink) { this.portfolioLink = portfolioLink; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<CompletedProject> getCompletedProjects() { return completedProjects; }
    public void setCompletedProjects(List<CompletedProject> completedProjects) {
        this.completedProjects = completedProjects;
        if (completedProjects != null) {
            completedProjects.forEach(p -> p.setProfile(this));
        }
    }
}