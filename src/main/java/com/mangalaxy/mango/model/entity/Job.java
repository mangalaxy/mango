package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the published job by the employer.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "job")
public class Job extends BaseEntity {

  private String title;
  // TODO: Change to EmploymentType enum
  @Column(name = "employment_type")
  private String employmentType;

  @Column(name = "remote_flag")
  private Boolean isRemote;

  @Column(name = "relocate_flag")
  private Boolean isRelocate;

  @Column(name = "sponsorship_flag")
  private Boolean isVisaSponsorship;

  private String experience;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @SortNatural
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_job_employer_id"))
  private Employer publisher;

  @Column(name = "job_role")
  private String jobRole;

  @Column(name = "publish_date")
  private LocalDate publishedOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
