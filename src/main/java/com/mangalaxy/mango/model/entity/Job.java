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

  @Column(name = "xp_range")
  private String xpRange;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "location_id_fk"))
  private Location location;

  @SortNatural
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id",
              foreignKey = @ForeignKey(name = "job_id_fk")),
        inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id",
              foreignKey = @ForeignKey(name = "skill_id_fk"))
  )
  private Set<Skill> skills = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id", updatable = false, nullable = false,
        foreignKey = @ForeignKey(name = "employer_id_fk"))
  private Employer publisher;

  @Column(name = "job_role")
  private String jobRole;

  @Column(name = "published_on")
  private LocalDate publishedOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
