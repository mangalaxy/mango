package com.mangalaxy.mango.domain.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a blog topic.
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
  @SequenceGenerator(name = "topicSeqGenerator", sequenceName = "topic_seq", allocationSize = 10)
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "topic",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        orphanRemoval = true
  )
  private Set<Post> posts = new HashSet<>();

}

