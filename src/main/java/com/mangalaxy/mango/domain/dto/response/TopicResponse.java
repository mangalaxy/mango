package com.mangalaxy.mango.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponse {
  private Integer id;
  private String title;
}
