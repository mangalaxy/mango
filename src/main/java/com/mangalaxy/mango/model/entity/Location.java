package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a predefined geo location.
 * <p>
 * It's includes only a specific country and city, it does not include
 * such geographical attributes as latitude and longitude.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "location")
public class Location extends BaseEntity {

  @Column(nullable = false, length = 30)
  private String city;

  @Column(nullable = false, length = 30)
  private String country;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private Set<Job> jobs = new HashSet<>();

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private Set<Talent> talents = new HashSet<>();

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private Set<Employer> employers = new HashSet<>();

}
