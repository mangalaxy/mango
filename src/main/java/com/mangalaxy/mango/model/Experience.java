package com.mangalaxy.mango.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Experience {

  private String company;

  private String position;

  @Embedded
  private ActivityPeriod period;

  @Column(name = "worked")
  private Boolean isWorked;

  private String description;

}
