package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Setter
@Getter
public class Education {

  private String institution;
  // TODO: Change to 'Degree' enum
  private String degree;
  private String specialization;
  private LocalDate startedDate;
  private LocalDate graduationDate;

}
