package com.mangalaxy.mango.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Education {

  /**
   * Convenient method for obtaining possible degrees.
   *
   * @return array of possible degrees.
   */
  public static Degree[] degrees() {
    return Degree.values();
  }

  private String institution;

  @Column(length = 8)
  @Enumerated(EnumType.STRING)
  private Degree degree;

  private String specialization;

  @Embedded
  private ActivityPeriod period;

  public enum Degree {
    ASSOCIATE,  // Associate degree
    BACHELOR,   // Bachelor's degree
    MASTER,     // Master's degree
    PHD         // Doctoral degree
  }

}
