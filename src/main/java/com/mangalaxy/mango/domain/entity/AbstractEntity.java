package com.mangalaxy.mango.domain.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * Abstract base entity class to derive entity classes from.
 *
 * @author Yuri Podolsky
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity extends AbstractPersistable<Long> {

  @Column(name = "created_date", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  @Column(name = "last_update")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

}
