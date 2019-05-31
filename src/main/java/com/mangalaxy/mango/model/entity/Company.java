package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@Table(name = "company",
      uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "name_unique"))
public class Company extends BaseEntity {

  @NotBlank
  @Size(max = 60)
  @Column(name = "name")
  private String name;

  @Column(name = "logo_url")
  private String logoUrl;

  @Column(name = "headline")
  private String headline;

  @Column(name = "address")
  private String address;

  @Column(name = "size")
  private String size;

  @Size(max = 60)
  @Column(name = "industry")
  private String industry;

  @Column(name = "media_url")
  private String mediaUrl;

  @Column(name = "about")
  private String aboutText;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "company_skill",
          joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "company_id_fk")),
          inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "skill_id_fk"))
  )
  private Set<Skill> techStack = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_perks")
  @Column(name = "item")
  private Set<String> perks = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_benefits")
  @Column(name = "item")
  private Set<String> benefits = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "company_links")
  @Column(name = "reference")
  private Set<String> links = new HashSet<>();

  @ElementCollection
  @Column(name = "photo_url")
  private Set<String> gallery = new HashSet<>();

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private Set<Employer> recruiters = new HashSet<>();

  @Column(name = "created_on")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
