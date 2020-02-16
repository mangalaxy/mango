package com.mangalaxy.mango.domain.entity;

import com.mangalaxy.mango.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
  @SequenceGenerator(name = "userSequence", sequenceName = "users_id_seq")
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  private String email;

  private String password;
  private boolean enabled;

  @ElementCollection
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role_name", nullable = false)
  @Enumerated(EnumType.STRING)
  private Set<Role> roles = new HashSet<>();

  public User() {
    super();
    this.enabled=false;
  }
}

