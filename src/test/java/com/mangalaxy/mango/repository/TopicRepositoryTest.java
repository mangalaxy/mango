package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Topic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@DataJpaTest
class TopicRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TopicRepository topicRepository;

  private Topic topic1;
  private Topic topic2;

  @BeforeEach
  void setUp() {
    topic1 = new Topic();
    topic1.setTitle("Interview process");
    topic2 = new Topic();
    topic2.setTitle("Job search");
    entityManager.persist(topic1);
    entityManager.persist(topic2);
    entityManager.flush();
  }

  @AfterEach
  void tearDown() {
    entityManager.remove(topic1);
    entityManager.remove(topic2);
    entityManager.clear();
  }

  @Test
  @DisplayName("Find all topics")
  void shouldFindAllTopics() {
    List<Topic> actualTopics = (List<Topic>) topicRepository.findAll();
    assertThat(actualTopics, hasSize(2));
    assertThat(actualTopics, containsInAnyOrder(equalTo(topic1), equalTo(topic2)));

  }
}