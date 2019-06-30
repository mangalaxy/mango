package com.mangalaxy.mango.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  USER;

  @Override
  public String getAuthority() {
    return this.toString();
  }
}
