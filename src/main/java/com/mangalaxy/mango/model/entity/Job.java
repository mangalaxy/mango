package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the published job by the employer in a company department.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "job")
public class Job extends BaseEntity {

  @NotBlank
  @Size(max = 60)
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

  @Column(name = "experience")
  private String experience;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "job_location_id_fk"))
  private Location location;

  @SortNatural
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "job_skill",
          joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id",
                  foreignKey = @ForeignKey(name = "job_join_job_skill_fk")),
          inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id",
                  foreignKey = @ForeignKey(name = "skill_join_job_skill_fk"))
  )
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id", updatable = false, nullable = false,
          foreignKey = @ForeignKey(name = "job_employer_id_fkey"))
  private Employer publisher;

  @Column(name = "job_role")
  private String jobRole;

  @Column(name = "published_on")
  private LocalDate publishedOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
