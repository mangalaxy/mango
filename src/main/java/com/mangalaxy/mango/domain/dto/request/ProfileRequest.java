package com.mangalaxy.mango.model.dto.request;

import lombok.Data;

@Data
public class ProfileRequest {
  private Long id;
  private String photoUrl;
  private String selectedJobRole;
}
