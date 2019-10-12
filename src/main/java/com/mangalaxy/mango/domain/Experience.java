package com.mangalaxy.mango.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
