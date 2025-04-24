package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByTakenFalse();
    List<Project> findByTakenBy(User user);
    List<Project> findByCreatedBy(User user);
    List<Project> findByCreatedByAndMustBeCheckedTrue(User user);
}