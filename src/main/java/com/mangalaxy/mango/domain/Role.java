package com.mangalaxy.mango.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Determines user roles in authorization process.
 */
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
