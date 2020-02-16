package com.mangalaxy.mango.domain.entity;

import com.mangalaxy.mango.domain.Degree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Education {

  @Column(name = "institution")
  private String institution;

  @Column(name = "degree")
  @Enumerated(EnumType.STRING)
  private Degree degree;

  @Column(name = "specialization")
  private String specialization;

  @Embedded
  private ActivityPeriod period;

}
