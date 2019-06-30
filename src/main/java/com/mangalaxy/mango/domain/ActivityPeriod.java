package com.mangalaxy.mango.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ActivityPeriod {

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "finish_date")
  private LocalDate finishDate;
}
