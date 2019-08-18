package com.mangalaxy.mango.model.dto.response;

import lombok.Data;

@Data
public class ProfileResponse {
  private Long id;
  private String photoUrl;
  private String selectedJobRole;
}
