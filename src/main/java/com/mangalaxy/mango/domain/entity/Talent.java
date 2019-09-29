package com.mangalaxy.mango.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "talent")
@NaturalIdCache
@ApiModel(description = "All details about the Talent")
public class Talent extends AbstractEntity {

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  @ApiModelProperty(notes = "The database generated talent ID")
  private String fullName;

  @NotBlank
  @Email(message = "Invalid email")
  @Size(max = 60)
  @NaturalId
  @ApiModelProperty(notes = "The talent email")
  private String email;

  @NotBlank
  @Size(min = 6, max = 100)
  @ApiModelProperty(notes = "The talent password")
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  @EqualsAndHashCode.Exclude
  @ApiModelProperty(notes = "The talent location")
  private Location location;

  @ManyToMany(mappedBy = "matchedTalents")
  private Set<Employer> matchedEmployers;

}
