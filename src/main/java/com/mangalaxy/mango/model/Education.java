package com.mangalaxy.mango.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public
class Education {

  private String institution;

  @Enumerated(EnumType.STRING)
  private Degree degree;

  private String specialization;

  @Embedded
  private ActivityPeriod period;

}
