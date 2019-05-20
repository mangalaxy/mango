package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "job")
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Include
  private Long id;

  private String title;
  // TODO: Change to EmploymentType enum
  @Column(name = "employment_type")
  private String employmentType;

  @Column(name = "remote")
  private Boolean isRemote;

  @Column(name = "relocation")
  private Boolean isRelocate;

  @Column(name = "visa_sponsorship")
  private Boolean isVisaSponsorship;

  private String experience;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @SortNatural
  @ManyToMany(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST
  })
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer publisher;

  @Column(name = "job_role")
  private String jobRole;

  @Column(name = "publish_date")
  private LocalDate publishedOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
