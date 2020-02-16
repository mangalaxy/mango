package com.mangalaxy.mango.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a post that published in the mangostart blog.
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
  @SequenceGenerator(name = "postSeq", sequenceName = "post_id_seq", allocationSize = 10)
  @Column(name = "id", nullable = false, unique = true, updatable = false)
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

  @Column(name = "author")
  private String author;

  @Singular
  @ElementCollection
  @CollectionTable(name = "post_tags")
  @Column(name = "tag")
  private Set<String> tags = new HashSet<>();

}
