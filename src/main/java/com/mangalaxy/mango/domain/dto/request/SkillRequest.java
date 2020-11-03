package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(doNotUseGetters = true)
public class SkillRequest {
  private final String name;

  @JsonCreator
  public SkillRequest(@JsonProperty("name") String name) {
    this.name = name;
  }
}
