package com.mangalaxy.mango.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer")
@NaturalIdCache
@EntityListeners({AuditingEntityListener.class})
public class Employer extends AbstractEntity {

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  private String fullName;

  @NotBlank
  @Email(message = "Invalid email")
  @Size(max = 60)
  @Column(name = "email")
  @NaturalId
  private String workEmail;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @Size(max = 18)
  @Column(name = "phone")
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;

  @Column(name = "job_title", length = 30)
  private String jobTitle;

  @Column(name = "photo_url")
  private String photoUrl;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "location_id", nullable = false,
        foreignKey = @ForeignKey(name = "location_id_fk"))
  private Location location;

  @OneToMany(mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Set<Job> openJobs = new HashSet<>();

  public void addJob(Job job) {
    openJobs.add(job);
    job.setPublisher(this);
  }

  public void removeJob(Job job) {
    openJobs.remove(job);
    job.setPublisher(null);
  }

}

