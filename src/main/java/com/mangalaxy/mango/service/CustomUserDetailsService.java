package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
  JwtAuthenticationResponse signIn(LoginRequest request);

  UserDetails loadUserById(Long id);

  ApiResponse registerNewUser(LoginRequest request);
}
