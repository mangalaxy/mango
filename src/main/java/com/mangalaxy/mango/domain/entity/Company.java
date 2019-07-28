package com.mangalaxy.mango.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the company entity with details.
 *
 * @see Employer
 * @see Job
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company")
public class Company extends AbstractEntity {

  @NotBlank
  @Size(max = 60)
  private String name;

  @Column(name = "logo_url")
  private String logoUrl;

  private String headline;

  private String address;

  private String size;

  @Size(max = 60)
  private String industry;

  @Column(name = "media_url")
  private String mediaUrl;

  private String about;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "company_skill",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> skills = new HashSet<>();

  @ElementCollection
  @Column(name = "name")
  private Set<String> perks = new HashSet<>();

  @ElementCollection
  @Column(name = "name")
  private Set<String> benefits = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_links")
  @Column(name = "url")
  private Set<String> links = new HashSet<>();

  @ElementCollection
  @Column(name = "photo_url")
  private Set<String> gallery = new HashSet<>();

  @OneToMany(mappedBy = "company", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  private Set<Employer> recruiters = new HashSet<>();

}
