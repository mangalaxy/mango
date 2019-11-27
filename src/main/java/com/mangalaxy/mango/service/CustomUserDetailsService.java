package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.request.PasswordRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.repository.UserRepository;
import com.mangalaxy.mango.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

import javax.servlet.http.HttpServletRequest;

public interface CustomUserDetailsService extends UserDetailsService {
  JwtAuthenticationResponse signIn(LoginRequest request);
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    return UserPrincipal.create(user);
  }

  ApiResponse registerNewUser(LoginRequest request);

  ApiResponse resetPassword(HttpServletRequest request, String userEmail);

  JwtAuthenticationResponse changePassword(long id, String token);

  ApiResponse savePassword(PasswordRequest password);
}
