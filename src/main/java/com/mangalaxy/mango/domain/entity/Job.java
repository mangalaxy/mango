package com.mangalaxy.mango.domain.entity;

import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the published job by the employer in a company.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true,
      onlyExplicitlyIncluded = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSequence")
  @SequenceGenerator(name = "jobSequence", sequenceName = "job_id_seq")
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "job_role_id")
  private JobRole jobRole;

  @Column(name = "employment_type")
  private String employmentType;

  @Column(name = "is_remote")
  private Boolean remote;

  @Column(name = "relocation")
  private Boolean relocation;

  @Column(name = "visa_sponsorship")
  private Boolean visaSponsorship;

  @Column(name = "job_experience", nullable = false)
  private String requiredExperience;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @SortNatural
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id"))
  private Set<Skill> skills = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employer_id", nullable = false)
  private Employer publisher;

}
