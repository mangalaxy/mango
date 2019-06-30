package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.Employer;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class UserDetailsImpl implements UserDetails {
  private Employer employer;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return employer.getRoles();
  }

  @Override
  public String getPassword() {
    return employer.getPassword();
  }

  @Override
  public String getUsername() {
    return employer.getWorkEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
