package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
