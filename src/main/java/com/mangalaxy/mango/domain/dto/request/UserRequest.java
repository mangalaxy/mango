package com.mangalaxy.mango.domain.dto.request;

import lombok.Data;

@Data
public class UserRequest {
  private String email;
  private String password;
}
