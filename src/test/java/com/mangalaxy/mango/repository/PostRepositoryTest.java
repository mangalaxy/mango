package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PostRepository postRepository;

  private Post post1;
  private Post post2;

  @Before
  public void setUp() {
    // given
    Topic topic = new Topic();
    topic.setTitle("Interviewing");

    post1 = Post.builder()
          .title("Ready to Shuffle Up Your Tired Interview Process?")
          .description("When the top brass comes in to your HQ for a board meeting")
          .body("When the top brass comes in to your HQ for a board meeting...")
          .topic(topic)
          .tag("interview")
          .author("Rob Stevenson")
          .countViews(0)
          .countLikes(0)
          .build();
    post1.setCreatedDate(LocalDateTime.of(2019, Month.DECEMBER, 12, 12, 45));

    post2 = Post.builder()
          .title("5 Interview Questions Hiring Managers Need to Ask Before Making An Offer")
          .description("Job interviews can be time consuming fact-finding missions that don't" +
                " always yield the best results. Sometimes you find out six months…")
          .body("Job interviews can be time consuming fact-finding missions that don’t always yield the best results.")
          .topic(topic)
          .tag("hiring")
          .tag("interview")
          .author("Whitney Ricketts")
          .countViews(0)
          .countLikes(0)
          .build();
    post2.setCreatedDate(LocalDateTime.of(2020, Month.JANUARY, 8, 9, 12));

    // Perform bidirectional synchronization
    topic.addPost(post1);
    topic.addPost(post2);
    entityManager.persistAndFlush(topic);
  }

  @Test
  public void shouldFindPostsByTopicName_thenSuccess() {
    String expectedPostTitle1 = "Ready to Shuffle Up Your Tired Interview Process?";
    String expectedPostTitle2 = "5 Interview Questions Hiring Managers Need to Ask Before Making An Offer";
    String topicTitle = "Interviewing";
    Pageable pageable = PageRequest.of(0, 20);
    // when
    Page<Post> postPage = postRepository.findAllByTopic_Title(topicTitle, pageable);
    // then
    List<Post> actual = postPage.getContent();
    assertThat(actual, hasSize(2));
    assertThat(actual, contains(equalTo(post1), equalTo(post2)));
    assertEquals(expectedPostTitle1, actual.get(0).getTitle());
    assertEquals(expectedPostTitle2, actual.get(1).getTitle());
    assertEquals(topicTitle, actual.get(0).getTopic().getTitle());
    assertEquals(topicTitle, actual.get(1).getTopic().getTitle());
  }

  @Test
  public void shouldFindPostsByTopicNameAndSortedDescByCreatedDate_thenSuccess() {
    String topicName = "Interviewing";
    Sort createdDateDesc = Sort.by(Sort.Direction.DESC, "createdDate");
    Pageable pageRequest = PageRequest.of(0, 20, createdDateDesc);
    // when
    Page<Post> postPage = postRepository.findAllByTopic_Title(topicName, pageRequest);
    // then
    List<Post> actual = postPage.getContent();
    assertThat(actual, hasSize(2));
    assertThat(actual, containsInRelativeOrder(equalTo(post2), equalTo(post1)));
    assertEquals(topicName, actual.get(0).getTopic().getTitle());
  }

  @Test
  public void whenPostNotFoundReturnEmptyOptional_thenSuccess() {
    String postTitle = "Where to Begin When Opening a New Role";
    Optional<Post> actualPost = postRepository.findByTitle(postTitle);
    assertEquals(Optional.empty(), actualPost);
  }

  @Test
  public void shouldFindPostByTitle_thenSuccess() {
    String postTitle = "Ready to Shuffle Up Your Tired Interview Process?";
    Optional<Post> actualPost = postRepository.findByTitle(postTitle);
    assertNotEquals(Optional.empty(), actualPost);
    assertEquals(postTitle, actualPost.get().getTitle());
    assertNotNull(actualPost.get().getId());
  }

}
