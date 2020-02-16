package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
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

  @Column(name = "level", nullable = false)
  @Enumerated(EnumType.STRING)
  private Level level;

  @Column(name = "name", nullable = false)
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
