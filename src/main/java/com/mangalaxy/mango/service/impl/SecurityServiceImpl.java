package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.entity.PasswordResetToken;
import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.repository.PasswordResetTokenRepository;
import com.mangalaxy.mango.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;

//@Service
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
  private final PasswordResetTokenRepository passwordResetTokenRepository;

  @Override
  public String validatePasswordResetToken(long id, String token) {
    PasswordResetToken passToken =
        passwordResetTokenRepository.findByToken(token);
    if ((passToken == null) || (passToken.getUser()
        .getId() != id)) {
      return "invalidToken";
    }

    Calendar cal = Calendar.getInstance();
    if ((passToken.getExpiryDate()
        .getTime() - cal.getTime()
        .getTime()) <= 0) {
      return "expired";
    }

    final User user = passToken.getUser();

    final Authentication auth = new UsernamePasswordAuthenticationToken(
        user, null, Arrays.asList(
        new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
    SecurityContextHolder.getContext().setAuthentication(auth);
    return null;
  }
}
