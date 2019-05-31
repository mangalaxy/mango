package com.mangalaxy.mango.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Language {

  @NotBlank
  @Size(max = 30)
  private String name;

  @Enumerated(EnumType.STRING)
  private LangLevel level;
}
