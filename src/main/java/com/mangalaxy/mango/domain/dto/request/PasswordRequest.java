package com.mangalaxy.mango.domain.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {
  private String oldPassword;
  private String newPassword;
}
