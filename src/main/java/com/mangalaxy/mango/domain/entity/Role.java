package com.mangalaxy.mango.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  USER,
  TALENT,
  EMPLOYER,
  ADMIN;

  @Override
  public String getAuthority() {
    return this.toString();
  }
}
