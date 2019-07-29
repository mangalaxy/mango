package com.mangalaxy.mango.model.dto.request;

import lombok.Data;

@Data
public class TalentRequest {
  private Long id;
  private String fullName;
  private String email;
  private String password;
  private LocationRequest location;
}
