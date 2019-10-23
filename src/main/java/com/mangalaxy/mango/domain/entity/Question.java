package com.mangalaxy.mango.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Question extends AbstractEntity {

  @NotBlank
  private String message;

  @ManyToOne
  @JoinColumn(name = "employer_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Employer employer;

  @NotBlank
  @ManyToOne
  @JoinColumn(name = "talent_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Talent talent;

  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Answer answer;
}
