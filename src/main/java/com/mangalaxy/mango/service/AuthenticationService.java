package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;

public interface AuthenticationService {
  JwtAuthResponse authenticate(String username, String password);
  void logout(String username);
}
