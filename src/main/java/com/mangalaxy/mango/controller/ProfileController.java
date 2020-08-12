package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/talents")
@Api(value = "Profile Data API")
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping("/{talentId}/profile")
  @ApiOperation(value = "Get talent by profile id")
  public ResponseEntity<ProfileResponse> getProfileByOwner(
      @ApiParam(value = "Talent id from which Profile object will retrieve", required = true)
      @PathVariable Long talentId) {
    ProfileResponse profileResponse = profileService.fetchTalentProfile(talentId);
    return ResponseEntity.ok(profileResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @GetMapping("/me/profile")
  @ApiOperation(value = "Get profile for current authorized user")
  public ResponseEntity<ProfileResponse> getCurrentTalentProfile() {
    ProfileResponse profileResponse = profileService.fetchAuthorizedTalentProfile();
    return ResponseEntity.ok(profileResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/me/profile")
  @ApiOperation(value = "Update profile for current authorized user")
  public ResponseEntity<ProfileResponse> updateCurrentProfile(@RequestBody ProfileRequest profileRequest) {
    ProfileResponse profileResponse = profileService.updateAuthorizedTalentProfile(profileRequest);
    return ResponseEntity.ok(profileResponse);
  }
}
