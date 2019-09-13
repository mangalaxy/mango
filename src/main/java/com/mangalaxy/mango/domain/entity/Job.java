package com.mangalaxy.mango.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the published job by the employer in a company department.
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "job")
public class Job extends AbstractEntity {

  @NotBlank
  @Size(max = 60)
  private String title;
  // TODO: Change to EmploymentType enum
  @Column(name = "employment_type")
  private String employmentType;

  @Column(name = "is_remote")
  private Boolean isRemote;

  @Column(name = "is_relocate")
  private Boolean isRelocate;

  @Column(name = "is_sponsorship")
  private Boolean isVisaSponsorship;

  @Column(name = "xp_range")
  private String xpRange;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  @EqualsAndHashCode.Exclude
  private Location location;

  @SortNatural
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id", updatable = false, nullable = false)
  @EqualsAndHashCode.Exclude
  private Employer publisher;

  @Column(name = "job_role")
  private String jobRole;

}
