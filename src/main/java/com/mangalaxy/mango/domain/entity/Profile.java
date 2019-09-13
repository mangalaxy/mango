package com.mangalaxy.mango.domain.entity;

import com.mangalaxy.mango.domain.CandidateStatus;
import com.mangalaxy.mango.domain.Education;
import com.mangalaxy.mango.domain.Experience;
import com.mangalaxy.mango.domain.Language;
import com.mangalaxy.mango.domain.Salary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
@ApiModel(description = "All details about the Profile")
public class Profile {

  @Id
  @ApiModelProperty(notes = "The database generated profile ID")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id", nullable = false)
  @ApiModelProperty(notes = "The profile talent owner")
  private Talent owner;

  @Column(name = "photo_url")
  @ApiModelProperty(notes = "The profile owner photo")
  private String photoUrl;

  @Column(name = "job_role")
  @ApiModelProperty(notes = "The profile job role")
  private String selectedJobRole;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "profile_skill",
        joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
  )
  @ApiModelProperty(notes = "List of profile skills")
  private Set<Skill> skills = new HashSet<>();

  @Enumerated(EnumType.STRING)
  @Column(length = 12)
  @ApiModelProperty(notes = "The profile status")
  private CandidateStatus status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  @ApiModelProperty(notes = "The profile owner location")
  private Location preferredLocation;

  @Embedded
  private Salary preferredSalary;

  @ElementCollection
  @Column(name = "point")
  private Set<String> expectations;

  @ElementCollection
  @CollectionTable(name = "talent_preferences")
  @Column(name = "company_type")
  private List<String> preferredCompanyType;

  @ElementCollection
  @ApiModelProperty(notes = "List of profile experiences")
  private List<Experience> experiences;

  @ElementCollection
  @ApiModelProperty(notes = "List of profile educations")
  private List<Education> educations;

  @ElementCollection
  @CollectionTable(name = "talent_languages")
  @ApiModelProperty(notes = "List of profile languages")
  private List<Language> preferredLang;

}
