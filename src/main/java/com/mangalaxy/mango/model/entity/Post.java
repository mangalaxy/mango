package com.mangalaxy.mango.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
@Data
public class Post extends BaseEntity {

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "body")
  private String body;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "count_views")
  private Integer countViews;

  @Column(name = "count_likes")
  private Integer countLikes;

  @ManyToOne
  @JoinColumn(name = "topic")
  private Topic topic;

  @Column(name = "created_by")
  private String createdBy;
}
