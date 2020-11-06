package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(doNotUseGetters = true)
public class SkillRequest {
  String name;

  @JsonCreator
  public SkillRequest(
        @JsonProperty("name") String name) {
    this.name = name;
  }
}
