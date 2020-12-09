package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;
import com.mangalaxy.mango.service.AuthenticationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authentication API", description = "Allows users to log in or log out using the provided credentials")
@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationService authenticationService;

  @PostMapping("/api/v1/auth/login")
  public ResponseEntity<JwtAuthResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
    JwtAuthResponse jwtResponse = authenticationService.authenticate(loginRequest);
    return ResponseEntity.ok(jwtResponse);
  }

  @GetMapping("/api/v1/auth/logout")
  public ResponseEntity<ApiResponse> logout(Authentication authentication) {
    if (authentication != null) {
      SecurityContextHolder.getContext().setAuthentication(null);
      ApiResponse logoutResponse = new ApiResponse(true, "User logged out successfully");
      return ResponseEntity.ok(logoutResponse);
    } else {
      ApiResponse failedResponse = new ApiResponse(false, "User not logged in");
      return ResponseEntity.ok(failedResponse);
    }
  }

}
