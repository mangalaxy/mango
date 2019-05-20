package com.mangalaxy.mango.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "profile")
public class Profile {

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private Talent talent;


  @Column(name = "created_date")
  private LocalDate createdOn;

  @Column(name = "last_update")
  private LocalDate updatedOn;
}
