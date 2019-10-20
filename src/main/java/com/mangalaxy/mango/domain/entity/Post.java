package com.mangalaxy.mango.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a post that published in the site blog.
 *
 * @see Topic
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeqGenerator")
  @SequenceGenerator(name = "postSeqGenerator", sequenceName = "post_seq", allocationSize = 10)
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "count_views")
  private Integer countViews;

  @Column(name = "count_likes")
  private Integer countLikes;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "topic_id", nullable = false)
  private Topic topic;

  @Column(name = "created_by")
  private String author;

  @ElementCollection
  @Column(name = "tag")
  private Set<String> tags = new HashSet<>();

}
