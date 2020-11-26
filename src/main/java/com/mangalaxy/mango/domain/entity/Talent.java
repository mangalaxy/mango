package com.mangalaxy.mango.domain.entity;

import com.mangalaxy.mango.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@NaturalIdCache
@Entity
@Table(name = "talent")
public class Talent extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talent_sequence")
  @SequenceGenerator(
        name = "talent_sequence",
        sequenceName = "talent_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @NaturalId
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(name = "role", nullable = false)
  private Role role;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "bookmarkedTalents")
  private Set<Employer> potentialEmployers = new HashSet<>();

}
