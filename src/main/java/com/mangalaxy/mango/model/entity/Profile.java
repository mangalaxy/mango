package com.mangalaxy.mango.model.entity;

import com.mangalaxy.mango.model.CandidateStatus;
import com.mangalaxy.mango.model.Language;
import com.mangalaxy.mango.model.Salary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
  private Long id;

  @OneToOne(mappedBy = "profile")
  @MapsId
  @JoinColumn(name = "talent_id", foreignKey = @ForeignKey(name = "FK_profile_talent_id"))
  @EqualsAndHashCode.Include
  private Talent owner;

  @Column(name = "photo_url")
  private String photoUrl;

  @Column(name = "job_role")
  private String selectedJobRole;

  @Enumerated(EnumType.STRING)
  private CandidateStatus status;

  @OneToOne
  @JoinColumn(name = "location_id")
  private Location preferredLocation;

  @Embedded
  private Salary preferredSalary;

  @ElementCollection
  private Set<String> expectations;

  @ElementCollection
  @CollectionTable(name = "talent_preferences")
  private Set<String> preferredCompanyType;

  @ElementCollection
  private Set<Experience> experiences = new HashSet<>();

  @ElementCollection
  private Set<Education> educations = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "talent_languages")
  private Set<Language> preferredLang = new HashSet<>();

  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
