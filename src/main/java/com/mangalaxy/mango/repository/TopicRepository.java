package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Short> {

}
