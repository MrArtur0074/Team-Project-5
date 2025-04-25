package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Profile;
import com.bezkoder.springjwt.payload.response.CompletedProjectResponse;
import com.bezkoder.springjwt.repository.CompletedProjectRepository;
import com.bezkoder.springjwt.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompletedProjectService {
    @Autowired
    private CompletedProjectRepository completedProjectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<CompletedProjectResponse> getCompletedProjectsByProfileId(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow();
        return completedProjectRepository.findByProfile(profile)
                .stream()
                .map(cp -> new CompletedProjectResponse(
                        cp.getId(),
                        cp.getSubmissionLink(),
                        cp.getStatus(),
                        cp.getProject()
                ))
                .collect(Collectors.toList());
    }
}