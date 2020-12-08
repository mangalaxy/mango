package com.mangalaxy.mango.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {
  @NotBlank
  private String oldPassword;
  @NotBlank
  @Size(min = 6, max = 100)
  private String newPassword;
}
