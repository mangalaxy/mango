package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.response.JwtAuthResponse;
import com.mangalaxy.mango.security.jwt.JwtTokenProvider;
import com.mangalaxy.mango.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class DefaultAuthenticationService implements AuthenticationService {
  private final UserDetailsService customUserDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  @Override
  public JwtAuthResponse authenticate(String username, String password) {
    UserDetails principal = customUserDetailsService.loadUserByUsername(username);
    if (passwordEncoder.matches(password, principal.getPassword())) {
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                  principal.getUsername(),
                  principal.getPassword()
            )
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtTokenProvider.generateToken(authentication);
      return new JwtAuthResponse(jwt);

    } else {
      log.error("User with username {} provided invalid password", username);
      throw new BadCredentialsException("Passwords do not matches");
    }
  }

  @Override
  public void logout(String username) {
    // TODO: Implement business logic
  }

}
