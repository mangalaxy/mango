package com.mangalaxy.mango.model.entity;


import com.mangalaxy.mango.model.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "topic")
@NoArgsConstructor
public class Topic extends BaseEntity{

  private String name;

  @ElementCollection
  @CollectionTable(name = "topic_tag", joinColumns = @JoinColumn(name = "topic_id"))
  @Column(name = "tag_id")
  private Set<Tag> tags;

  @OneToMany(mappedBy = "topic", orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Post> posts = new HashSet<>();

  public void addPost(Post post) {
    posts.add(post);
    post.setTopic(this);
  }
}
