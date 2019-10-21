package com.mangalaxy.mango.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "question")
public class QuestionMessage extends AbstractEntity {

  @NotBlank
  private String message;

  @NotBlank
  @ManyToOne
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @NotBlank
  @ManyToOne
  @JoinColumn(name = "talent_id")
  private Talent talent;

  @NotBlank
  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  private AnswerMessage answer;
}
