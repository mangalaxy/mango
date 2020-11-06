package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PostRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PostRepository postRepository;

  private Post post1;
  private Post post2;

  @BeforeEach
  void setUp() {
    // given
    Topic topic = new Topic();
    topic.setTitle("Interviewing");

    post1 = Post.builder()
          .headline("Ready to Shuffle Up Your Tired Interview Process?")
          .opening("When the top brass comes in to your HQ for a board meeting")
          .content("When the top brass comes in to your HQ for a board meeting...")
          .topic(topic)
          .author("Rob Stevenson")
          .viewsCount(0)
          .likesCount(0)
          .build();
    post1.setCreatedDate(LocalDateTime.of(2019, Month.DECEMBER, 12, 12, 45));

    post2 = Post.builder()
          .headline("5 Interview Questions Hiring Managers Need to Ask Before Making An Offer")
          .opening("Job interviews can be time consuming fact-finding missions that don't" +
                " always yield the best results. Sometimes you find out six months…")
          .content("Job interviews can be time consuming fact-finding missions that don’t always yield the best results.")
          .topic(topic)
          .author("Melanie Warren")
          .viewsCount(0)
          .likesCount(0)
          .build();
    post2.setCreatedDate(LocalDateTime.of(2020, Month.JANUARY, 8, 9, 12));

    // Perform bidirectional synchronization
    topic.addPost(post1);
    topic.addPost(post2);
    entityManager.persistAndFlush(topic);
  }

  @AfterEach
  void tearDown() {
    entityManager.remove(post1);
    entityManager.remove(post2);
    entityManager.clear();
  }

  @Test
  void shouldFindPostsByTopicName_thenSuccess() {
    String expectedPostTitle1 = "Ready to Shuffle Up Your Tired Interview Process?";
    String expectedPostTitle2 = "5 Interview Questions Hiring Managers Need to Ask Before Making An Offer";
    String topicTitle = "Interviewing";
    Pageable pageable = PageRequest.of(0, 20);
    // when
    Page<Post> postPage = postRepository.findAllByTopic_Title(topicTitle, pageable);
    // then
    List<Post> actual = postPage.getContent();
    assertThat(actual, hasSize(2));
    assertThat(actual, containsInAnyOrder(equalTo(post1), equalTo(post2)));
    assertEquals(topicTitle, actual.get(0).getTopic().getTitle());
    assertEquals(topicTitle, actual.get(1).getTopic().getTitle());
  }

  @Test
  @DisplayName("Find posts by the topic name")
  void shouldFindPostsByTopicNameAndSortedDescByCreatedDate_thenSuccess() {
    String topicTitle = "Interviewing";
    Sort createdDateDesc = Sort.by(Sort.Direction.DESC, "createdDate");
    Pageable pageRequest = PageRequest.of(0, 20, createdDateDesc);
    // when
    Page<Post> postPage = postRepository.findAllByTopic_Title(topicTitle, pageRequest);
    // then
    List<Post> actual = postPage.getContent();
    assertThat(actual, hasSize(2));
    assertThat(actual, containsInRelativeOrder(equalTo(post2), equalTo(post1)));
    assertEquals(topicTitle, actual.get(0).getTopic().getTitle());
  }

  @Test
  void whenPostNotFoundReturnEmptyOptional_thenSuccess() {
    String postHeadline = "Where to Begin When Opening a New Role";
    Optional<Post> actualPost = postRepository.findByHeadline(postHeadline);
    assertEquals(Optional.empty(), actualPost);
  }

  @Test
  void shouldFindPostByTitle_thenSuccess() {
    String postHeadline = "Ready to Shuffle Up Your Tired Interview Process?";
    Optional<Post> actualPost = postRepository.findByHeadline(postHeadline);
    assertNotEquals(Optional.empty(), actualPost);
    assertEquals(postHeadline, actualPost.get().getHeadline());
    assertNotNull(actualPost.get().getId());
  }

}
