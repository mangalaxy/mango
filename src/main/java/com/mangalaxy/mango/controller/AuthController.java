package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final CustomUserDetailsService customUserDetailsService;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(customUserDetailsService.signIn(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody LoginRequest loginRequest) {
    ApiResponse response = customUserDetailsService.registerNewUser(loginRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
