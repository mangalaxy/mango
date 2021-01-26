package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;
import com.mangalaxy.mango.security.jwt.JwtTokenProvider;
import com.mangalaxy.mango.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultAuthenticationService implements AuthenticationService {
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  @Override
  public JwtAuthResponse authenticate(LoginRequest loginRequest) {
    Authentication authRequest = new UsernamePasswordAuthenticationToken(
          loginRequest.getEmail(),
          loginRequest.getPassword()
    );
    Authentication authentication = authenticationManager.authenticate(authRequest);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenProvider.generateToken(authentication);
    return new JwtAuthResponse(jwt);
  }

}
