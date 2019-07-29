package com.mangalaxy.mango;

import com.mangalaxy.mango.model.entity.Post;
import com.mangalaxy.mango.model.entity.Topic;
import com.mangalaxy.mango.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PostRepository postRepository;

  @Before
  public void serUpData() {
    Topic topic = new Topic();

    topic.setName("Topic1");
    testEntityManager.persistAndFlush(topic);

    Post post = Post.builder()
        .title("TestTitle")
        .description("TestDescription")
        .body("TestBody")
        .countLikes(10)
        .countViews(10)
        .topic(topic).build();

    Post post1 = Post.builder().title("Topic-1")
        .description("Desc")
        .body("Body")
        .countViews(5)
        .countLikes(3)
        .topic(topic).build();

    testEntityManager.persistAndFlush(post1);
    testEntityManager.persistAndFlush(post);
  }

  @Test
  public void findPostByTopicNameTest() {
    String expectedPostTitle = "TestTitle";
    String topicName = "Topic1";
    Pageable pageable = PageRequest.of(0, 100);

    Post post = postRepository.findAllByTopic_Name(topicName, pageable).getContent().get(1);

    assertEquals(expectedPostTitle, post.getTitle());

  }

  @Test
  public void findByTopicNameAndSortTest() {
    String expectedPostTitle = "TestTitle";
    String topicName = "Topic1";
    Pageable pageable = PageRequest.of(0, 100);

    List<Post> posts = postRepository.findAllByTopic_Name(topicName, Sort.by("countLikes").descending().and(Sort.by("countViews").descending()));

    assertEquals(expectedPostTitle, posts.get(0).getTitle());
  }

}
