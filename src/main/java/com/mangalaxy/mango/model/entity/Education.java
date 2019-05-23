package com.mangalaxy.mango.model.entity;

import com.mangalaxy.mango.model.ActivityPeriod;
import com.mangalaxy.mango.model.Degree;
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
class Education {

  private String institution;

  @Enumerated(EnumType.STRING)
  private Degree degree;

  private String specialization;

  @Embedded
  private ActivityPeriod period;

}
