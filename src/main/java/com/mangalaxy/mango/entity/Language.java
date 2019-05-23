package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
class Language {
  @Column(nullable = false, length = 60)
  private String name;
  @Enumerated(EnumType.STRING)
  private Level level;
}
