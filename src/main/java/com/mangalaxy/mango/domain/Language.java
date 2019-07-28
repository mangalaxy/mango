package com.mangalaxy.mango.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Language {

  /**
   * Contains language proficiency levels.
   */
  public enum Level {
    ELEMENTARY,
    INTERMEDIATE,
    ADVANCED,
    FLUENT
  }

  @Column(length = 15)
  @Enumerated(EnumType.STRING)
  private Level level;

  @NotBlank
  @Size(max = 30)
  private String name;

  /**
   * Convenient method for obtaining available language levels.
   *
   * @return array of available degrees.
   */
  public static Level[] levels() {
    return Level.values();
  }

}
