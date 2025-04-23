package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Profile;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProfileRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestBody Profile profileRequest) {
        Optional<User> userOpt = userRepository.findById(userDetails.getId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        if (profileRepository.existsByUser(user)) {
            return ResponseEntity.badRequest().body("Profile already exists");
        }

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setBio(profileRequest.getBio());
        profile.setPortfolioLink(profileRequest.getPortfolioLink());
        profile.setCompletedProjects(profileRequest.getCompletedProjects()); // если они передаются

        profileRepository.save(profile);

        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<?> getOwnProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<User> userOpt = userRepository.findById(userDetails.getId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Optional<Profile> profileOpt = profileRepository.findByUser(userOpt.get());

        if (profileOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Profile not found");
        }

        return ResponseEntity.ok(profileOpt.get());
    }
}