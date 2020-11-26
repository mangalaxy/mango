package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.security.CurrentUser;
import com.mangalaxy.mango.security.UserPrincipal;
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

  @PreAuthorize("hasRole('TALENT')")
  @GetMapping("/api/v1/talents/{talentId}/profile")
  public ResponseEntity<ProfileResponse> getSpecifiedTalentProfile(@PathVariable Long talentId) {
    ProfileResponse profile = profileService.fetchTalentProfile(talentId);
    return ResponseEntity.ok(profile);
  }

  @PreAuthorize("hasRole('TALENT')")
  @GetMapping("/api/v1/talents/me/profile")
  public ResponseEntity<ProfileResponse> getAuthorizedTalentProfile(@CurrentUser UserPrincipal principal) {
    ProfileResponse profile = profileService.fetchTalentProfile(principal.getId());
    return ResponseEntity.ok(profile);
  }

  @PreAuthorize("hasRole('TALENT')")
  @PutMapping("/api/v1/talents/me/profile")
  public ResponseEntity<ProfileResponse> updateAuthorizedTalentProfile(@CurrentUser UserPrincipal principal,
                                                                       @RequestBody ProfileRequest profileRequest) {
    ProfileResponse profile = profileService.updateProfileByTalentId(principal.getId(), profileRequest);
    return ResponseEntity.ok(profile);
  }
}
