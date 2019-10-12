package com.mangalaxy.mango.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "All details about the Job")
public class Job extends AbstractEntity {

  @NotBlank
  @Size(max = 60)
  private String title;
  // TODO: Change to EmploymentType enum
  @Column(name = "employment_type")
  @ApiModelProperty(notes = "Job employment type")
  private String employmentType;

  @Column(name = "is_remote")
  @ApiModelProperty(notes = "Is remote job flag")
  private Boolean isRemote;

  @Column(name = "is_relocate")
  @ApiModelProperty(notes = "Is relocate job flag")
  private Boolean isRelocate;

  @Column(name = "is_sponsorship")
  @ApiModelProperty(notes = "Is visa sponsored for job flag")
  private Boolean isVisaSponsorship;

  @Column(name = "xp_range")
  @ApiModelProperty(notes = "Job xp range")
  private String xpRange;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "Job location")
  private Location location;

  @SortNatural
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  @ApiModelProperty(notes = "List of job skills")
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id", updatable = false, nullable = false)
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "Job publisher")
  private Employer publisher;

  @Column(name = "job_role")
  @ApiModelProperty(notes = "Job role")
  private String jobRole;

}
