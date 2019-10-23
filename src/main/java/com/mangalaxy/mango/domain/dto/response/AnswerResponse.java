package com.mangalaxy.mango.domain.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AnswerResponse {
  private Long id;
  private String message;
  private LocalDateTime createdDate;
}
