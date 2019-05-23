package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Setter
@Getter
public class Experience {

  private String company;
  private String position;
  private LocalDate startedDate;
  private LocalDate finishedDate;
  private Boolean isWorked;
  private String description;
}
