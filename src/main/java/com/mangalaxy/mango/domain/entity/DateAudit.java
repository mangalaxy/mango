package com.mangalaxy.mango.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Abstract audit entity class to derive entity classes from.
 */
@Getter
@Setter
@ToString(doNotUseGetters = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class DateAudit {

  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "last_update")
  private LocalDateTime modifiedDate;

}
