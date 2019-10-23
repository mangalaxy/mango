package com.mangalaxy.mango.domain.dto.request;

import lombok.Data;

@Data
public class AnswerRequest {
  private Long id;
  private String message;
  private QuestionRequest question;
}
