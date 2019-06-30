package com.mangalaxy.mango.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Experience {

  private String company;

  @Column(length = 60)
  private String position;

  @Embedded
  private ActivityPeriod period;

  @Column(name = "is_working")
  private Boolean isWorking;

  private String description;

}
