package com.mangalaxy.mango.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer")
@NaturalIdCache
@ApiModel(description = "All details about the Employer")
public class Employer extends AbstractEntity {

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  @ApiModelProperty(notes = "The employer full name")
  private String fullName;

  @NotBlank
  @Email(message = "Invalid email")
  @Size(max = 60)
  @Column(name = "email")
  @NaturalId
  @ApiModelProperty(notes = "The employer work email")
  private String workEmail;

  @NotBlank
  @Size(min = 6, max = 100)
  @ApiModelProperty(notes = "The employer password")
  private String password;

  @Size(max = 18)
  @Column(name = "phone")
  @ApiModelProperty(notes = "The employer phone number")
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @ApiModelProperty(notes = "The employer company")
  private Company company;

  @Column(name = "job_title", length = 30)
  @ApiModelProperty(notes = "The employer job title")
  private String jobTitle;

  @Column(name = "photo_url")
  @ApiModelProperty(notes = "The employer photo")
  private String photoUrl;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "location_id", nullable = false,
        foreignKey = @ForeignKey(name = "location_id_fk"))
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "The employer location")
  private Location location;

  @OneToMany(mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "List of employer open jobs")
  private Set<Job> openJobs = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "matched_talents",
        joinColumns = {@JoinColumn(name = "employer_id")},
        inverseJoinColumns = {@JoinColumn(name = "talent_id")})
  @ToString.Exclude
  private Set<Talent> matchedTalents = new HashSet<>();

  public void addJob(Job job) {
    openJobs.add(job);
    job.setPublisher(this);
  }

  public void removeJob(Job job) {
    openJobs.remove(job);
    job.setPublisher(null);
  }

}

