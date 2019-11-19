package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.domain.entity.VerificationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.WebRequest;

public interface CustomUserDetailsService extends UserDetailsService {
  JwtAuthenticationResponse signIn(LoginRequest request);

  UserDetails loadUserById(Long id);

  ApiResponse registerNewUser(LoginRequest loginRequest,
                              BindingResult result,
                              WebRequest request,
                              Errors errors);


  ApiResponse confirmRegistration(WebRequest request, String token);

  User getUser(String verificationToken);

  void saveRegisteredUser(User user);

  void createVerificationToken(User user, String token);

  VerificationToken getVerificationToken(String VerificationToken);
}