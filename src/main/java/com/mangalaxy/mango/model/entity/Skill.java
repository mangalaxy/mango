package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "skill")
public class Skill extends BaseEntity {

  @NotBlank
  @Size(max = 30)
  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "skills")
  private Set<Job> jobs = new HashSet<>();

  @ManyToMany(mappedBy = "techStack")
  private Set<Company> companies = new HashSet<>();

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "last_update")
  private LocalDateTime updatedOn;

}
