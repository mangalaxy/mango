package com.mangalaxy.mango.domain;

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
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Language {

  @NotBlank
  @Size(max = 30)
  private String name;

  @Column(length = 12)
  @Enumerated(EnumType.STRING)
  private LangLevel level;

}
