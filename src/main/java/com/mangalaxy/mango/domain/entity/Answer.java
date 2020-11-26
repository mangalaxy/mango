package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(
      callSuper = true,
      doNotUseGetters = true,
      onlyExplicitlyIncluded = true
)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends DateAudit {

  @Id
  @JoinColumn(name = "question_id", nullable = false)
  private Long id;

  @Column(name = "message", nullable = false)
  private String message;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Question question;

}

