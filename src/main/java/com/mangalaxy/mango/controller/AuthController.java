package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.request.PasswordRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

  private final CustomUserDetailsService customUserDetailsService;

  @PostMapping("/auth/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(customUserDetailsService.signIn(loginRequest));
  }

  @PostMapping("/auth/signUp")
  public ResponseEntity<?> registerUser(@RequestBody LoginRequest loginRequest,
                                        BindingResult result,
                                        WebRequest request,
                                        Errors errors) {
    ApiResponse response = customUserDetailsService.registerNewUser(loginRequest, result, request, errors);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/password/reset")
  public ResponseEntity<ApiResponse> resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
    ApiResponse response = customUserDetailsService.resetPassword(request, userEmail);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/password/change")
  public ResponseEntity<?> changePassword(@RequestParam("id") long id, @RequestParam("token") String token) {
    return ResponseEntity.ok(customUserDetailsService.changePassword(id, token));
  }

  @PostMapping("/password/save")
  public ResponseEntity<ApiResponse> savePassword(@RequestBody PasswordRequest password) {
    ApiResponse response = customUserDetailsService.savePassword(password);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/registration/confirm")
  public ResponseEntity<?> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
    return ResponseEntity.ok(customUserDetailsService.confirmRegistration(request, token));
  }
}
