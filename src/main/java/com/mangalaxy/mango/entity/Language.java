package com.mangalaxy.mango.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Language {

  private String name;
  // TODO: Define english level properly
  private String level;
}
