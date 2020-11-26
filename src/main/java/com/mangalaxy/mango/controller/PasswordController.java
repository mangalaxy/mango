package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.PasswordChangeRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.service.PasswordManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PasswordController {
  private final PasswordManagementService passwordManagementService;

  @PutMapping("/api/v1/password/reset")
  public ResponseEntity<ApiResponse> resetPasswordByEmail(@RequestParam("email") String email) {
    ApiResponse response = passwordManagementService.resetPassword(email);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAnyRole('TALENT', 'EMPLOYER')")
  @PatchMapping("/api/v1/password/update")
  public ResponseEntity<ApiResponse> updatePassword(@RequestParam("clientId") Long clientId,
                                                    @RequestBody PasswordChangeRequest passwordPair) {
    ApiResponse response = passwordManagementService.changePassword(clientId, passwordPair);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/api/v1/password/set")
  public ResponseEntity<ApiResponse> setNewPassword(@RequestParam("token") String token,
                                                    @RequestBody String newPassword) {
    ApiResponse response = passwordManagementService.setPassword(token, newPassword);
    return ResponseEntity.ok(response);
  }



}
