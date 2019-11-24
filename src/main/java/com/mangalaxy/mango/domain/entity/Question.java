package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionSequence")
  @SequenceGenerator(name = "questionSequence", sequenceName = "question_id_seq")
  private Long id;

  @Column(name = "message")
  private String message;

  @NotBlank
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @NotBlank
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "talent_id")
  private Talent talent;

  @NotBlank
  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  private Answer answer;

}
