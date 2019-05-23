package com.mangalaxy.mango.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base entity class to derive entity classes from.
 *
 * @author Yuri Podolsky
 */
@EqualsAndHashCode
@Getter
@MappedSuperclass
public abstract class Essential {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
