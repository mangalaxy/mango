package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(
      callSuper = true,
      doNotUseGetters = true,
      onlyExplicitlyIncluded = true
)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skill")
public class Skill extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
  @SequenceGenerator(
        name = "skill_sequence",
        sequenceName = "skill_id_seq",
        allocationSize = 1
  )
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
