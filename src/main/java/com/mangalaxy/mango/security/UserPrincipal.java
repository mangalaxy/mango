package com.mangalaxy.mango.security;

import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Talent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal extends User {
  private final Long id;
  private final String fullName;

  public UserPrincipal(Long id, String fullName, String username, String password,
                       Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.id = id;
    this.fullName = fullName;
  }

  /**
   * Factory method to create authenticated user using employer entity.
   *
   * @param employer employer from database
   * @return instance of authenticated user
   */
  public static UserPrincipal from(Employer employer) {
    List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
          new SimpleGrantedAuthority(employer.getRole().name())
    );
    return new UserPrincipal(
          employer.getId(),
          employer.getFullName(),
          employer.getEmail(),
          employer.getPassword(),
          grantedAuthorities
    );
  }

  /**
   * Factory method to create authenticated user using talent entity.
   * 
   * @param talent talent from database
   * @return instance of authenticated user
   */
  public static UserPrincipal from(Talent talent) {
    List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
          new SimpleGrantedAuthority(talent.getRole().name())
    );
    return new UserPrincipal(
          talent.getId(),
          talent.getFullName(),
          talent.getEmail(),
          talent.getPassword(),
          grantedAuthorities
    );
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      return this.getUsername().equals(((UserPrincipal) obj).getUsername());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getUsername().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      return this.getUsername().equals(((UserPrincipal) obj).getUsername());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getUsername().hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getName()).append(" [");
    sb.append("Id=").append(this.id).append(", ");
    sb.append("FullName=").append(this.fullName).append(", ");
    sb.append("Username=").append(getUsername()).append(", ");
    sb.append("Password=[PROTECTED], ");
    sb.append("Enabled=").append(isEnabled()).append(", ");
    sb.append("AccountNonExpired=").append(isAccountNonExpired()).append(", ");
    sb.append("credentialsNonExpired=").append(isCredentialsNonExpired()).append(", ");
    sb.append("AccountNonLocked=").append(isAccountNonLocked()).append(", ");
    sb.append("Granted Authorities=").append(getAuthorities()).append("]");
    return sb.toString();
  }


}
