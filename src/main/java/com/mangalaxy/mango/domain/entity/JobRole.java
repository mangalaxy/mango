package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_role_sequence")
  @SequenceGenerator(
        name = "job_role_sequence",
        sequenceName = "job_role_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Short id;

  @Column(name = "title", nullable = false)
  private String title;

  @ToString.Exclude
  @OneToMany(mappedBy = "jobRole")
  private Set<Job> jobs = new HashSet<>();

  public void addJob(Job job) {
    jobs.add(job);
    job.setJobRole(this);
  }

}
