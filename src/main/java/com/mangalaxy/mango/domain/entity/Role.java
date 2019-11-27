package com.mangalaxy.mango.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  USER,
  TALENT,
  EMPLOYER,
  ADMIN,
  CHANGE_PASSWORD_PRIVILEGE;

  @Override
  public String getAuthority() {
    return this.toString();
  }
}
