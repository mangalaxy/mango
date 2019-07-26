package com.mangalaxy.mango.dto.response;

import lombok.Data;

@Data
public class TalentResponse {
  private Long id;
  private String fullName;
  private String email;
  private LocationResponse location;
}
