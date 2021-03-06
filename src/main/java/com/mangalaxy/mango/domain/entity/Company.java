package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "company")
public class Company extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence")
  @SequenceGenerator(
        name = "company_sequence",
        sequenceName = "company_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "headline")
  private String headline;

  @Column(name = "logo_url")
  private String logoUrl;

  @Column(name = "size")
  private String size;

  @Column(name = "headquarters_address")
  private String headquartersAddress;

  @Column(name = "industry")
  private String industry;

  @Column(name = "promo_url")
  private String promoUrl;

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

  @OneToMany(
        mappedBy = "company",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE }
  )
  private Set<Employer> employers = new HashSet<>();

}
