package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping("/api/v1/talents/{talentId}/profile")
  public ResponseEntity<ProfileResponse> getSpecifiedTalentProfile(@PathVariable Long talentId) {
    ProfileResponse profileResponse = profileService.fetchTalentProfile(talentId);
    return ResponseEntity.ok(profileResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @GetMapping("/api/v1/talents/me/profile")
  public ResponseEntity<ProfileResponse> getAuthorizedTalentProfile() {
    ProfileResponse profileResponse = profileService.fetchAuthorizedTalentProfile();
    return ResponseEntity.ok(profileResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/api/v1/talents/me/profile")
  public ResponseEntity<ProfileResponse> updateAuthorizedTalentProfile(@RequestBody ProfileRequest profileRequest) {
    ProfileResponse profileResponse = profileService.updateAuthorizedTalentProfile(profileRequest);
    return ResponseEntity.ok(profileResponse);
  }
}
