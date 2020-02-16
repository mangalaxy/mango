package com.mangalaxy.mango.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Entity
@Table(name = "job_role")
public class JobRole {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobRoleSequence")
  @SequenceGenerator(name = "jobRoleSequence", sequenceName = "job_role_id_seq")
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Short id;

  @Column(name = "title", nullable = false)
  private String title;

  @OneToMany(mappedBy = "jobRole")
  private Set<Job> jobs = new HashSet<>();

}
