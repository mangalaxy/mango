package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.service.ProfileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Talent Profile API", description = "Provides read and update operations on talent profile resource")
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
  public ResponseEntity<ProfileResponse> getAuthorizedTalentProfile(Authentication authentication) {
    if (authentication != null) {
      UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
      ProfileResponse profile = profileService.fetchTalentProfile(principal.getId());
      return ResponseEntity.ok(profile);
    } else {
      throw new IllegalStateException("UserDetails instance should not null");
    }

  }

  @PreAuthorize("hasRole('TALENT')")
  @PutMapping("/api/v1/talents/me/profile")
  public ResponseEntity<ProfileResponse> updateAuthorizedTalentProfile(@RequestBody ProfileRequest profileRequest,
                                                                       Authentication authentication) {
    if (authentication != null) {
      UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
      ProfileResponse profile = profileService.updateProfileByTalentId(principal.getId(), profileRequest);
      return ResponseEntity.ok(profile);
    } else {
      throw new IllegalStateException("UserDetails instance should not null");
    }

  }
}
