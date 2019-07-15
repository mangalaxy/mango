package com.mangalaxy.mango.model.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "topics")
public class Topic extends BaseEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "tags")
  private String tags;

  @OneToMany(mappedBy = "topic")
  private List<Post> posts;
}
