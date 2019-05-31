package com.mangalaxy.mango.model.entity;

import com.mangalaxy.mango.model.CandidateStatus;
import com.mangalaxy.mango.model.Education;
import com.mangalaxy.mango.model.Experience;
import com.mangalaxy.mango.model.Language;
import com.mangalaxy.mango.model.Salary;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
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

  @Enumerated(EnumType.STRING)
  @Column(length = 12)
  private CandidateStatus status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id",
        foreignKey = @ForeignKey(name = "location_id_fk"))
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

}
