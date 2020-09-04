package com.mangalaxy.mango.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentResponse {
  private Long id;
  private String fullName;
  private String email;
  private LocationResponse location;
}
