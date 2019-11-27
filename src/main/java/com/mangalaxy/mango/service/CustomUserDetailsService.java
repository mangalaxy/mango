package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.request.PasswordRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface CustomUserDetailsService extends UserDetailsService {
  JwtAuthenticationResponse signIn(LoginRequest request);

  UserDetails loadUserById(Long id);

  ApiResponse registerNewUser(LoginRequest request);

  ApiResponse resetPassword(HttpServletRequest request, String userEmail);

  JwtAuthenticationResponse changePassword(long id, String token);

  ApiResponse savePassword(PasswordRequest password);
}
