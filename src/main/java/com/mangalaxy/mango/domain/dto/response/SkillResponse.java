package com.mangalaxy.mango.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class SkillResponse {
  Long id;
  String name;
}
