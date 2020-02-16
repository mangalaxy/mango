package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

  @Autowired
  private TestEntityManager manager;

  @Autowired
  private PostRepository postRepository;

  @Before
  public void setUp() {
    // given
    Topic topic = new Topic();
    topic.setTitle("Career");
    log.info("Persisted topic with id: {}", manager.persistAndGetId(topic));

    Post post1 = Post.builder()
          .title("10 Tips for the best talent interviewing")
          .body("Ten tips for the best talent interviewing...")
          .topic(topic)
          .tag("negotation")
          .tag("tips")
          .tag("interviews")
          .build();
    post1.setCreatedDate(LocalDateTime.parse("2019-07-29T02:50:19.787"));

    Post post2 = Post.builder()
          .title("Are Product Managers the New Software Engineers?")
          .body("We starting our stories how PM...")
          .topic(topic)
          .tag("promotion")
          .build();
    post2.setCreatedDate(LocalDateTime.parse("2019-07-29T02:50:20.787"));

    Set<Post> posts = new HashSet<>();
    posts.add(post1);
    posts.add(post2);
    topic.setPosts(posts);

    log.info("Persisted post with id: {}", manager.persistAndGetId(post1));
    log.info("Persisted post with id: {}", manager.persistAndGetId(post2));
    manager.flush();
  }

  @Test
  public void shouldFindPostByTopicName_thenSuccess() {
    String expectedTitle = "Are Product Managers the New Software Engineers?";
    String topicTitle = "Career";
    Pageable pageable = PageRequest.of(0, 20);
    // when
    Page<Post> page = postRepository.findAllByTopic_Title(topicTitle, pageable);
    Post post = page.getContent().get(1);
    // then
    assertThat(page).isNotEmpty();
    assertEquals(expectedTitle, post.getTitle());
  }

  @Test
  public void shouldFindByTopicNameAndSortedByCreatedDate_thenSuccess() {
    String expectedTitle = "Are Product Managers the New Software Engineers?";
    String topicName = "Career";

    Pageable pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdDate"));
    Page<Post> posts = postRepository.findAllByTopic_Title(topicName, pageRequest);
    assertEquals(expectedTitle, posts.getContent().get(0).getTitle());
  }

}
