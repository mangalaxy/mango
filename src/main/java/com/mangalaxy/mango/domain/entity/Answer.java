package com.mangalaxy.mango.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answerSequence")
  @SequenceGenerator(name = "answerSequence", sequenceName = "answer_id_seq")
  private Long id;

  @Column(name = "message", nullable = false)
  private String message;

  @OneToOne
  @MapsId
  @JoinColumn(name = "question_id", nullable = false)
  private Question question;

}

