package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.UserRequest;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.repository.UserRepository;
import com.mangalaxy.mango.security.JwtTokenProvider;
import com.mangalaxy.mango.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    return UserPrincipal.create(user);
  }

  @Override
  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + id)
    );

    return UserPrincipal.create(user);
  }

  @Override
  public JwtAuthenticationResponse signIn(UserRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return new JwtAuthenticationResponse(jwt);
  }
}
