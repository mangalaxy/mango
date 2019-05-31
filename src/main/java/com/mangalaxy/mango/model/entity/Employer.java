package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer")
public class Employer extends BaseEntity {

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  private String fullName;

  @Email(message = "Invalid email")
  @Size(max = 60)
  @Column(name = "email")
  private String workEmail;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @Size(max = 15)
  @Column(name = "phone")
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id",
          foreignKey = @ForeignKey(name = "employer_company_id_fkey"))
  private Company company;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "photo_url")
  private String photoUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", nullable = false,
          foreignKey = @ForeignKey(name = "employer_location_id_fkey"))
  private Location location;

  @OneToMany(mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Set<Job> openJobs = new HashSet<>();

  @Column(name = "created_on")
  private LocalDate createdOn;

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

