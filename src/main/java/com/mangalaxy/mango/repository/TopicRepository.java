package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
}
