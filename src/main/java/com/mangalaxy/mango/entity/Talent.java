package com.mangalaxy.mango.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "talent")
public class Talent extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Profile profile;

  @Column(name = "full_name", nullable = false, length = 60)
  private String fullName;

  @Column(name = "email", nullable = false, length = 60, unique = true)
  private String email;

  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id",
        foreignKey = @ForeignKey(name = "FK_talent_location_id"))
  private Location location;

  @Column(name = "registered_date")
  private LocalDate registeredOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;

}
