package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer")
public class Employer extends Essential {

  @Column(name = "full_name", nullable = false, length = 60)
  private String fullName;

  @Column(name = "email", nullable = false, length = 60, unique = true)
  private String workEmail;

  @Column(name = "password", nullable = false, length = 100)
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_employer_location_id"))
  private Location location;

  @OneToMany(
        mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  @JoinColumn(name = "job_id",
        foreignKey = @ForeignKey(name = "FK_employer_job_id"))
  private Set<Job> openJobs = new HashSet<>();

  @Column(name = "registration_date")
  private LocalDate registeredOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

  public void addJob(Job job) {
    openJobs.add(job);
    job.setPublisher(this);
  }

  public void removeJob(Job job) {
    openJobs.remove(job);
    job.setPublisher(null);
  }

}

