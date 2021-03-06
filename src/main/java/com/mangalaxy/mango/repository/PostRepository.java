package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  Page<Post> findAllByTopic_Title(String topicTitle, Pageable pageable);
  Page<Post> findAllByTopic_Id(Integer topicId, Pageable pageable);
  Optional<Post> findByHeadline(String title);

}
