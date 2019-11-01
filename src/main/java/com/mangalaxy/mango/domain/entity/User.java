package com.mangalaxy.mango.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

  private String email;
  private String password;
//  private String token;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role_id")
  private Set<Role> roles;

}
