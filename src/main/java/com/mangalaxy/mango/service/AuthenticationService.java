package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;

public interface AuthenticationService {
  JwtAuthResponse authenticate(LoginRequest loginRequest);
}
