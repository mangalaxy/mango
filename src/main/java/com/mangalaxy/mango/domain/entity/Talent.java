package com.mangalaxy.mango.domain.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talentSeq")
  @SequenceGenerator(name = "talentSeq", sequenceName = "talent_id_seq")
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

  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "bookmarkedTalents")
  private Set<Employer> potentialEmployers = new HashSet<>();

}
