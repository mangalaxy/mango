package com.mangalaxy.mango.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a company with the details.
 *
 * @see Employer
 * @see Job
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true,
      onlyExplicitlyIncluded = true)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySequence")
  @SequenceGenerator(name = "companySequence", sequenceName = "company_id_seq")
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "headline")
  private String headline;

  @Column(name = "logo_url")
  private String logo;

  @Column(name = "hq_address")
  private String headquartersAddress;

  @Column(name = "emp_count")
  private String size;

  @Column(name = "industry")
  private String industry;

  @Column(name = "promo_url")
  private String promo;

  @Column(name = "about")
  private String about;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "company_skill",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id"))
  private Set<Skill> skills = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_perks")
  @Column(name = "name", nullable = false)
  private Set<String> perks = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_benefits")
  @Column(name = "name", nullable = false)
  private Set<String> benefits = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_links")
  @Column(name = "url", nullable = false)
  private Set<String> links = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_photos")
  @Column(name = "photo_url", nullable = false)
  private Set<String> photos = new HashSet<>();

  @OneToMany(mappedBy = "company",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  private Set<Employer> employers = new HashSet<>();

}
