package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.UserRequest;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  JwtAuthenticationResponse signIn(UserRequest request);

  UserDetails loadUserById(Long id);
}
