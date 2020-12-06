package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@ToString(doNotUseGetters = true)
public class SkillRequest {

  @NotBlank
  String name;

  @JsonCreator
  public SkillRequest(
        @JsonProperty("name") String name) {
    this.name = name;
  }
}
