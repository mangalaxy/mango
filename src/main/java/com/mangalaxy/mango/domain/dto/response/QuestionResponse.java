package com.mangalaxy.mango.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionResponse {
  private Long id;
  private String message;
  private LocalDateTime createdDate;
  private AnswerResponse answer;
}
