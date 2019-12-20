package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
    final JwtAuthenticationResponse jwtResponse = authService.signIn(loginRequest);
    return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse> registerUser(@RequestBody LoginRequest loginRequest) {
    ApiResponse response = authService.registerNewUser(loginRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
