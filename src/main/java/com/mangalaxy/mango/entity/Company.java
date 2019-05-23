package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the company entity with details.
 *
 * @see Employer
 * @see Job
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company")
public class Company extends Essential {

  @Column(name = "name", nullable = false, length = 60)
  private String name;

  @Column(name = "logo_url")
  private String logoUrl;

  @Column(name = "headline")
  private String headline;

  @Column(name = "address")
  private String address;

  @Column(name = "size")
  private String size;

  @Column(name = "industry")
  private String industry;

  @Column(name = "media_url")
  private String mediaUrl;

  @Column(name = "about")
  private String aboutText;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "company_skill",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private Set<Skill> techStack = new HashSet<>();

  @ElementCollection
  private Set<String> perks = new HashSet<>();

  @ElementCollection
  private Set<String> benefits = new HashSet<>();

  @ElementCollection
  private Set<String> links = new HashSet<>();

  @ElementCollection
  private Set<String> gallery = new HashSet<>();

  @OneToMany(
        mappedBy = "company",
        cascade = CascadeType.ALL
  )
  private Set<Employer> recruiters = new HashSet<>();

  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

//  private Employer updatedBy;
}
