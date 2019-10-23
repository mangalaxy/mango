package com.mangalaxy.mango.domain.dto.request;

import lombok.Data;

@Data
public class QuestionRequest {

  private Long id;
  private String message;
  private EmployerRequest employer;
  private TalentRequest talent;

}
