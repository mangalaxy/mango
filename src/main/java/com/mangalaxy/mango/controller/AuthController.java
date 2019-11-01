package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.UserRequest;
import com.mangalaxy.mango.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;

  @PostMapping("signIn")
  public ResponseEntity<?> authenticateUser(@RequestBody UserRequest loginRequest) {
    return ResponseEntity.ok(userService.signIn(loginRequest));
  }
}
