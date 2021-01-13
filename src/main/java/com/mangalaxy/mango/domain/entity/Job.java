package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the published job by the employer in a company.
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode(
      callSuper = true,
      doNotUseGetters = true,
      onlyExplicitlyIncluded = true
)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
  @SequenceGenerator(
        name = "job_sequence",
        sequenceName = "job_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        optional = false
  )
  @JoinColumn(name = "job_role_id")
  private JobRole jobRole;

  @Column(name = "job_type")
  private String jobType;

  @Column(name = "remote")
  private Boolean remote;

  @Column(name = "relocation")
  private Boolean relocation;

  @Column(name = "visa_sponsorship")
  private Boolean visaSponsorship;

  @Column(name = "experience_required", nullable = false)
  private String experienceRequired;

  @Column(name = "description")
  private String description;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(
        name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> skills = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employer_id", nullable = false)
  private Employer publisher;

}
