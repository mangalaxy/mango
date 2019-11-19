package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LoginRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.domain.dto.response.JwtAuthenticationResponse;
import com.mangalaxy.mango.domain.entity.Role;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.domain.entity.VerificationToken;
import com.mangalaxy.mango.repository.UserRepository;
import com.mangalaxy.mango.repository.VerificationTokenRepository;
import com.mangalaxy.mango.security.JwtTokenProvider;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.util.EmailNotConfirmedException;
import com.mangalaxy.mango.util.OnRegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;
  private final ApplicationEventPublisher eventPublisher;
  private final VerificationTokenRepository tokenRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (!user.isEnabled()) {
      throw new EmailNotConfirmedException();
    }

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
  public ApiResponse registerNewUser(LoginRequest loginRequest, BindingResult result,
                                     WebRequest request,
                                     Errors errors) {
    User userFromDB = userRepository.findByEmail(loginRequest.getEmail());
    if (userFromDB != null) {
      return new ApiResponse(false, "Username with this email is already taken!");
    }

    Set<Role> roles = new HashSet<>();
    roles.add(Role.USER);

    User user = new User();
    user.setEmail(loginRequest.getEmail());
    user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
    user.setRoles(roles);

    User registeredUser = userRepository.save(user);

    String appUrl = request.getContextPath();
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent
        (registeredUser, request.getLocale(), appUrl));

    return new ApiResponse(true, "To confirm registration you need to confirm your email");
  }

  @Override
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

  @Override
  public ApiResponse confirmRegistration(WebRequest request, String token) {
    VerificationToken verificationToken = getVerificationToken(token);

    if (verificationToken == null) {
      return new ApiResponse(false, "Invalid Token");
    }

    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
      return new ApiResponse(false, "Token expired");
    }

    user.setEnabled(true);
    saveRegisteredUser(user);
    return new ApiResponse(true, "Email confirmed");
  }

  @Override
  public User getUser(String verificationToken) {
    User user = tokenRepository.findByToken(verificationToken).getUser();
    return user;
  }

  @Override
  public void saveRegisteredUser(User user) {
    userRepository.save(user);
  }

  @Override
  public void createVerificationToken(User user, String token) {
    VerificationToken myToken = new VerificationToken(token, user);
    tokenRepository.save(myToken);
  }

  @Override
  public VerificationToken getVerificationToken(String VerificationToken) {
    return tokenRepository.findByToken(VerificationToken);
  }
}
