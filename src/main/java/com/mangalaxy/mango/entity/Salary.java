package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
class Salary {

  @Column(scale = 2, precision = 7)
  private BigDecimal value;
  private String currency;
}
