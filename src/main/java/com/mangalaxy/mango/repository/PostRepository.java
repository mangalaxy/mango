package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findAllByTopic_Name(String topicName, Pageable pageable);

  List<Post> findAllByTopic_Name(String topicName, Sort sort);

}
