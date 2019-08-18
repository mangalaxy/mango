package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.model.dto.response.ProfileResponse;
import com.mangalaxy.mango.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping("/api/v1/talents/{talentId}/profile")
  public ResponseEntity<ProfileResponse> getProfileByOwner(@PathVariable Long talentId) {
    ProfileResponse profileResponse = profileService.getProfileByTalent(talentId);
    return ResponseEntity.ok(profileResponse);
  }
}
