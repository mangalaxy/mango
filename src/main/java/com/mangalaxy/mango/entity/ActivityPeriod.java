package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
class ActivityPeriod {
  @Column(name = "start_date")
  private LocalDate startDate;
  @Column(name = "finish_date")
  private LocalDate finishDate;
}
