package com.mangalaxy.mango.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a blog's topic.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicSeqGenerator")
  @SequenceGenerator(name = "topicSeqGenerator", sequenceName = "topic_id_seq", allocationSize = 10)
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "topic",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        orphanRemoval = true)
  private Set<Post> posts = new HashSet<>();

}

