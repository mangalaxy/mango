package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {

  @Id
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id", nullable = false)
  private Talent owner;

  @Column(name = "photo_url")
  private String photoUrl;

  @Column(name = "job_role")
  private String selectedJobRole;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "profile_skill",
        joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
  )
  private Set<Skill> skills = new HashSet<>();

  @Enumerated(EnumType.STRING)
  @Column(length = 12)
  private CandidateStatus status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location preferredLocation;

  @Embedded
  private Salary preferredSalary;

  @ElementCollection
  @CollectionTable(name = "profile_expectations")
  @Column(name = "title")
  private Set<String> expectations = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "talent_preferences")
  @Column(name = "company_type")
  private Set<String> preferredCompanyType = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "profile_experiences")
  private List<Experience> experiences;

  @ElementCollection
  @CollectionTable(name = "profile_educations")
  private List<Education> educations;

  @ElementCollection
  @CollectionTable(name = "talent_languages")
  private List<Language> preferredLanguages;

}
