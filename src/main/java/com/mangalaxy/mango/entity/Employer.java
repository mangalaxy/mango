package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employer")
public class Employer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "email")
  private String workEmail;

  @Column(name = "password")
  private String password;

  @Column(name = "phone")
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "photo_url")
  private String photoUrl;

  private Location location;

  @OneToMany(
        mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Set<Job> jobs = new HashSet<>();

  @Column(name = "registration_date")
  private LocalDate registeredOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

  public void addJob(Job job) {
    jobs.add(job);
    job.setPublisher(this);
  }

  public void removeJob(Job job) {
    jobs.remove(job);
    job.setPublisher(null);
  }

}

