package com.mangalaxy.mango.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
 * The topic entity that represents a blog's topic.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_sequence")
  @SequenceGenerator(
        name = "topic_sequence",
        sequenceName = "topic_id_seq",
        allocationSize = 1
  )
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
        mappedBy = "topic",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        orphanRemoval = true
  )
  private Set<Post> posts = new HashSet<>();

  /**
   * Add a new post to the topic and synchronize bidirectional association.
   * @param post a post to add
   */
  public void addPost(Post post) {
    post.setTopic(this);
    posts.add(post);
  }

  /**
   * Removes a specified post from the topic's set of posts.
   * @param post a post to delete
   */
  public void removePost(Post post) {
    posts.remove(post);
    post.setTopic(null);
  }

}

