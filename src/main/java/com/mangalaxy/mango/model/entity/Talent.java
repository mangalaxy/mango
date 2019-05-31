package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "talent")
@NaturalIdCache
public class Talent extends BaseEntity {

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  private String fullName;

  @NotBlank
  @Email(message = "Invalid email")
  @Size(max = 60)
  @NaturalId
  private String email;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "location_id_fk"))
  private Location location;

}
