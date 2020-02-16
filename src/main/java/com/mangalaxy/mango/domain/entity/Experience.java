package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Experience {

  @Column(name = "company_name", nullable = false)
  private String company;

  @Column(name = "position", nullable = false)
  private String position;

  @Column(name = "description")
  private String description;

  @Embedded
  private ActivityPeriod period;

  @Column(name = "working_now")
  private Boolean isWorking;

}
