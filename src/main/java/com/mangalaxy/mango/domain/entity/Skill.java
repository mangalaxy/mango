package com.mangalaxy.mango.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "skill")
public class Skill extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skillSeq")
  @SequenceGenerator(name = "skillSeq", sequenceName = "skill_id_seq")
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "skills")
  private Set<Job> jobs = new HashSet<>();

  @ManyToMany(mappedBy = "skills")
  private Set<Company> companies = new HashSet<>();

  @ManyToMany(mappedBy = "skills")
  private Set<Profile> profiles = new HashSet<>();

}
