package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  UserDetails loadUserById(Long id);

  ApiResponse registerNewUser(LoginRequest request);

  JwtAuthenticationResponse signIn(LoginRequest loginRequest);

}
