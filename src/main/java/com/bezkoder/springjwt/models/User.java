package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "takenBy", cascade = CascadeType.ALL)
  @JsonManagedReference(value = "user-taken-projects")
  private List<Project> takenProjects = new ArrayList<>();

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
  @JsonManagedReference(value = "user-created-projects")
  private List<Project> createdProjects = new ArrayList<>();

  @OneToMany
  @JoinTable(
          name = "user_completed_projects",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "project_id")
  )
  private List<Project> completedProjects = new ArrayList<>();

  public User() {}

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }

  public Set<Role> getRoles() { return roles; }

  public void setRoles(Set<Role> roles) { this.roles = roles; }

  public List<Project> getTakenProjects() { return takenProjects; }

  public void setTakenProjects(List<Project> takenProjects) { this.takenProjects = takenProjects; }

  public List<Project> getCreatedProjects() { return createdProjects; }

  public void setCreatedProjects(List<Project> createdProjects) { this.createdProjects = createdProjects; }

  public List<Project> getCompletedProjects() { return completedProjects; }

  public void setCompletedProjects(List<Project> completedProjects) { this.completedProjects = completedProjects; }
}