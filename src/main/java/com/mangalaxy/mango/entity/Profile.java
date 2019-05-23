package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "profile")
public class Profile {

  @Id
  @EqualsAndHashCode.Include
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Talent talent;

  @Column(name = "photo_url")
  private String photoUrl;

  private String selectedJobRole;

  private String jobStatus;

  @OneToOne
  private Location preferredLocation;

  @Embedded
  private Salary preferredSalary;

  @ElementCollection
  private Set<String> expectations;

  @ElementCollection
  private Set<String> preferredCompanyType;

  @ElementCollection
  @CollectionTable(name = "talent_experience")
  private Set<Experience> xpSet = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "talent_education")
  private Set<Education> eduSet = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "talent_language")
  private Set<Language> preferredLang = new HashSet<>();

  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
