package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a predefined geo-spatial location.
 * <p>
 * It's includes only a specific country and city, it does not include
 * such geographical attributes as latitude and longitude.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @NotBlank
  @Size(max = 30)
  private String city;

  @NotBlank
  @Size(max = 30)
  private String country;

  @OneToMany(mappedBy = "location")
  private Set<Job> jobs = new HashSet<>();

  @OneToMany(mappedBy = "location")
  private Set<Talent> talents = new HashSet<>();

  @OneToMany(mappedBy = "location")
  private Set<Employer> employers = new HashSet<>();

  public Location(@NotBlank String city, @NotBlank String country) {
    this.city = city;
    this.country = country;
  }

}
