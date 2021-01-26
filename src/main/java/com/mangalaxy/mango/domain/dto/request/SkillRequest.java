package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class SkillRequest {

  private Long id;

  @NotBlank
  private String name;

  @JsonCreator
  public SkillRequest(@JsonProperty("id") Long id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }
}
