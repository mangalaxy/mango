package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents specified skill.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "skill")
public class Skill extends Essential {

  @Column(name = "name", nullable = false, length = 30)
  private String name;
  // TODO: Change on JobRole enumeration
  @Column(name = "job_role")
  private String jobRole;

  @ManyToMany(mappedBy = "skills")
  private Set<Job> jobs = new HashSet<>();

  @ManyToMany(mappedBy = "techStack")
  private Set<Company> companies = new HashSet<>();

  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;
}
