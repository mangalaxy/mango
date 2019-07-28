package com.mangalaxy.mango;

import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import com.mangalaxy.mango.repository.PostRepository;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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
    Topic topic = new Topic("Career", Arrays.asList("negotation", "business"), null);

    Post post1 = Post.builder()
          .title("10 Tips for the best talent interviewing")
          .countLikes(1832)
          .countViews(48302)
          .topic(topic)
          .build();

    Post post2 = Post.builder()
          .title("Are Product Managers the New Software Engineers?")
          .countLikes(3)
          .countViews(57)
          .topic(topic)
          .build();

    Set<Post> posts = new HashSet<>();
    posts.add(post1);
    posts.add(post2);
    topic.setPosts(posts);
    manager.persistAndFlush(topic);
    manager.persistAndFlush(post1);
    manager.persistAndFlush(post2);
  }

  @Test
  public void shouldFindPostByTopicName_thenSuccess() {
    String expectedTitle = "10 Tips for the best talent interviewing";
    String topicName = "Career";
    Pageable pageable = PageRequest.of(0, 20);
    // when
    Page<Post> page = postRepository.findAllByTopic_Name(topicName, pageable);
    Post post = page.getContent().get(1);
    // then
    assertEquals(expectedTitle, post.getTitle());
  }

  @Test
  public void findByTopicNameAndSortTest() {
    String expectedPostTitle = "TestTitle";
    String topicName = "Topic1";
    Pageable pageable = PageRequest.of(0, 20);

    List<Post> posts = postRepository.findAllByTopic_Name(topicName, Sort.by("countLikes").descending().and(Sort.by("countViews").descending()));

    assertEquals(expectedPostTitle, posts.get(0).getTitle());
  }

}
