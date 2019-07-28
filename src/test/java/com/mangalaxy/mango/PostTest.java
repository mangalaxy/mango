package com.mangalaxy.mango;

import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import com.mangalaxy.mango.repository.PostRepository;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostTest {

  @Autowired
  private TestEntityManager manager;

  @Autowired
  private PostRepository postRepository;

  @Before
  public void setUp() {
    // given
    Topic topic = new Topic();
    topic.setName("Career");
    topic.setTags(Arrays.asList("negotation", "business"));
    log.info("Persisted topic with id: {}", manager.persistAndGetId(topic));

    Post post1 = Post.builder()
          .title("10 Tips for the best talent interviewing")
          .countLikes(1832)
          .countViews(48302)
          .topic(topic)
          .build();
    post1.setCreatedDate(LocalDateTime.now());

    Post post2 = Post.builder()
          .title("Are Product Managers the New Software Engineers?")
          .countLikes(3)
          .countViews(57)
          .topic(topic)
          .build();
    post2.setCreatedDate(LocalDateTime.now());

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
    String topicName = "Career";
    Pageable pageable = PageRequest.of(0, 20);
    // when
    Page<Post> page = postRepository.findAllByTopic_Name(topicName, pageable);
    Post post = page.getContent().get(1);
    // then
    assertThat(page).isNotEmpty();
    assertEquals(expectedTitle, post.getTitle());
  }

  @Test
  public void shouldFindByTopicNameAndSortedByCreatedDate_thenSuccess() {
    String expectedTitle = "Are Product Managers the New Software Engineers?";
    String topicName = "Career";

    Sort sortingCondition = Sort.by(Sort.Direction.DESC, "createdDate");
    List<Post> posts = postRepository.findAllByTopic_Name(topicName, sortingCondition);
    assertEquals(expectedTitle, posts.get(0).getTitle());
  }

}
