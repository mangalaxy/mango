package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a predefined geo-spatial location.
 * <p>
 * It's includes only a specific country and city, it does not include
 * such geographical attributes as latitude and longitude.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"city","country"})
@ToString(doNotUseGetters = true, of = {"id","city","country"})
@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationSeqGenerator")
  @SequenceGenerator(name = "locationSeqGenerator", sequenceName = "location_seq")
  @Column(name = "id", nullable = false, updatable = false)
  private Short id;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "location")
  private Set<Job> jobs = new HashSet<>();

  @OneToMany(mappedBy = "location")
  private Set<Talent> talents = new HashSet<>();

  @OneToMany(mappedBy = "location")
  private Set<Employer> employers = new HashSet<>();

  /**
   * A basic constructor with the required properties.
   * @param city a city.
   * @param country a country.
   */
  public Location(@NotBlank String city, @NotBlank String country) {
    this.city = city;
    this.country = country;
  }

}
