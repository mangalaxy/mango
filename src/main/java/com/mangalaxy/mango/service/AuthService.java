package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.Role;
import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.request.PasswordRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.PasswordResetToken;
import com.mangalaxy.mango.domain.entity.Role;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.repository.PasswordResetTokenRepository;
import com.mangalaxy.mango.repository.UserRepository;
import com.mangalaxy.mango.security.jwt.JwtTokenProvider;
import com.mangalaxy.mango.security.JwtTokenProvider;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.util.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;
  private final MailSenderServise mailSenderServise;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final SecuriyService securiyService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    return UserPrincipal.create(user);
  }

  @Override
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + id)
    );

    return UserPrincipal.create(user);
  }

  @Override
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

  @Override
  public ApiResponse resetPassword(HttpServletRequest request, String userEmail) {
    User user = userRepository.findByEmail(userEmail);

    if (user == null) {
      throw new EntityNotFoundException();
    }

    String token = UUID.randomUUID().toString();
    createPasswordResetTokenForUser(user, token);
    String message = "http://localhost:9000/api/v1/auth/changePassword?id=" +
        user.getId() + "&token=" + token;

    mailSenderServise.send(message, "Reset Password", user.getEmail());

    return new  ApiResponse(true, "To reset your password we send the letter to you email");
  }

  @Override
  public JwtAuthenticationResponse changePassword(long id, String token) {
    PasswordResetToken passToken =
        passwordResetTokenRepository.findByToken(token);
    if ((passToken == null) || (passToken.getUser()
        .getId() != id)) {
      throw new AppException("Invalid Token");
    }

    Calendar cal = Calendar.getInstance();
    if ((passToken.getExpiryDate()
        .getTime() - cal.getTime()
        .getTime()) <= 0) {
      throw new AppException("Token Expired");
    }

    final User user = passToken.getUser();

    final Authentication auth = new UsernamePasswordAuthenticationToken(
        user.getEmail(), user.getPassword(), Arrays.asList(
        new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
    SecurityContextHolder.getContext().setAuthentication(auth);

    String jwt = tokenProvider.generateToken(auth);
    return new JwtAuthenticationResponse(jwt);

  }

  @Override
  public ApiResponse savePassword(PasswordRequest password) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByEmail(authentication.getName());
    user.setPassword(passwordEncoder.encode(password.getNewPassword()));
    userRepository.save(user);

    return new ApiResponse(true, "Password was changed");
  }

  public PasswordResetToken createPasswordResetTokenForUser(User user, String token) {
    PasswordResetToken myToken = new PasswordResetToken(token, user);
    PasswordResetToken saved =  passwordResetTokenRepository.save(myToken);
    return saved;
  }

  @Override
  public JwtAuthenticationResponse signIn(LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
          loginRequest.getEmail(),
          loginRequest.getPassword()
    );
    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return new JwtAuthenticationResponse(jwt);
  }

}
