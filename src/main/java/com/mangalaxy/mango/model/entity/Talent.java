package com.mangalaxy.mango.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "talent")
@NaturalIdCache
public class Talent extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Profile profile;

  @NotBlank
  @Size(min = 6, max = 60)
  @Column(name = "full_name")
  private String fullName;

  @Email(message = "Invalid email")
  @Size(max = 60)
  @NaturalId
  private String email;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "talent_location_id_fk"))
  private Location location;

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "last_update")
  private LocalDateTime updatedOn;

}
