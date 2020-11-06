package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The post entity that published in the blog.
 *
 * @see Topic
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
  @SequenceGenerator(
        name = "post_sequence",
        sequenceName = "post_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Integer id;

  @Column(name = "headline", nullable = false)
  private String headline;

  @Column(name = "opening", nullable = false)
  private String opening;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "views_count")
  private Integer viewsCount;

  @Column(name = "likes_count")
  private Integer likesCount;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "topic_id", nullable = false)
  private Topic topic;

}
