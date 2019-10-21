package com.mangalaxy.mango.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class AnswerMessage extends AbstractEntity {

  @NotBlank
  private String message;

  @OneToOne
  @MapsId
  @JoinColumn(name = "question_id", nullable = false)
  private QuestionMessage question;

}
