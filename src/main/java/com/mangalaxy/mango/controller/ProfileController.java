package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping("talents/{talentId}/profile")
  public ResponseEntity<ProfileResponse> getProfileByOwner(@PathVariable Long talentId) {
    ProfileResponse profileResponse = profileService.getProfileByTalent(talentId);
    return ResponseEntity.ok(profileResponse);
  }

  @GetMapping("me/profile")
  public ResponseEntity<ProfileResponse> getCurretnTalentProfile() {
    ProfileResponse profileResponse = profileService.getCurrentTalentProfile();
    return ResponseEntity.ok(profileResponse);
  }

  @PutMapping("me/profile")
  public ResponseEntity<ProfileResponse> updateCurrentProfile(@RequestBody ProfileRequest profileRequest) {
    ProfileResponse profileResponse = profileService.updateCurrentProfile(profileRequest);
    return ResponseEntity.ok(profileResponse);
  }
}
