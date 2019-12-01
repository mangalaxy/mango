package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.Role;
import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.repository.UserRepository;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.security.jwt.JwtTokenProvider;
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

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;

  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
          () -> new UsernameNotFoundException("User not found with id : " + id)
    );
    return UserPrincipal.create(user);
  }

  @Transactional
  public ApiResponse registerNewUser(LoginRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      return new ApiResponse(false, "Username with this email is already taken!");
    }

    Set<Role> roles = new HashSet<>();
    roles.add(Role.USER);

    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRoles(roles);

    userRepository.save(user);

    return new ApiResponse(true, "User registered successfully");
  }

  public JwtAuthenticationResponse signIn(LoginRequest loginRequest) {
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
