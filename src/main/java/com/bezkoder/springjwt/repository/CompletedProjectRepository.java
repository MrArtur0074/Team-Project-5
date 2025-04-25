package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.CompletedProject;
import com.bezkoder.springjwt.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompletedProjectRepository extends JpaRepository<CompletedProject, Long> {
    List<CompletedProject> findByProfile(Profile profile);
}