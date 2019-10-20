package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Salary {

  @Column(name = "amount", scale = 2, precision = 7)
  private BigDecimal amount;

  @Column(name = "currency")
  private String currency;

}
