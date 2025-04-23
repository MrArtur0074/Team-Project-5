package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.payload.response.CompletedProjectResponse;
import com.bezkoder.springjwt.service.CompletedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/completed-projects")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompletedProjectController {

    @Autowired
    private CompletedProjectService completedProjectService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<CompletedProjectResponse>> getCompletedProjectsByProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(completedProjectService.getCompletedProjectsByProfileId(profileId));
    }
}