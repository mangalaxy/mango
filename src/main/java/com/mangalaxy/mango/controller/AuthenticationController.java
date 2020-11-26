package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;
import com.mangalaxy.mango.security.CurrentUser;
import com.mangalaxy.mango.security.CustomUserDetailsService;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final CustomUserDetailsService customUserDetailsService;

  @PostMapping("/api/v1/auth/login")
  public ResponseEntity<JwtAuthResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
    JwtAuthResponse jwtResponse = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
    return ResponseEntity.ok(jwtResponse);
  }

  @GetMapping("/api/v1/auth/logout")
  public ResponseEntity<?> logout(@CurrentUser UserPrincipal principal) {
    authenticationService.logout(principal.getUsername());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
