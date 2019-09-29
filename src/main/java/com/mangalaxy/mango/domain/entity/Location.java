package com.mangalaxy.mango.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@ApiModel(description = "All details about the Location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @ApiModelProperty(notes = "The database generated location ID")
  private Integer id;

  @NotBlank
  @Size(max = 30)
  @ApiModelProperty(notes = "The location city")
  private String city;

  @NotBlank
  @Size(max = 30)
  @ApiModelProperty(notes = "The location country")
  private String country;

  @OneToMany(mappedBy = "location")
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "List of jobs related with current location")
  private Set<Job> jobs = new HashSet<>();

  @OneToMany(mappedBy = "location")
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "List of talents related with current location")
  private Set<Talent> talents = new HashSet<>();

  @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "List of employers related with current location")
  private Set<Employer> employers = new HashSet<>();

  public Location(@NotBlank String city, @NotBlank String country) {
    this.city = city;
    this.country = country;
  }

}
