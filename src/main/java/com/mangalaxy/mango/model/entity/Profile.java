package com.mangalaxy.mango.model.entity;

import com.mangalaxy.mango.model.*;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "profile")
public class Profile {

  @Id
  private Long id;

  @OneToOne(mappedBy = "profile")
  @MapsId
  @JoinColumn(name = "talent_id", foreignKey = @ForeignKey(name = "profile_talent_id_fkey"))
  private Talent owner;

  @Column(name = "photo_url")
  private String photoUrl;

  @Column(name = "job_role")
  private String selectedJobRole;

  @Enumerated(EnumType.STRING)
  private CandidateStatus status;

    @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location preferredLocation;

  @Embedded
  private Salary preferredSalary;

  @ElementCollection
  private Set<String> expectations;

  @ElementCollection
  @CollectionTable(name = "talent_preferences")
  private List<String> preferredCompanyType;

  @ElementCollection
  private List<Experience> experiences;

  @ElementCollection
  private List<Education> educations;

  @ElementCollection
  @CollectionTable(name = "talent_languages")
  private List<Language> preferredLang;

  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
