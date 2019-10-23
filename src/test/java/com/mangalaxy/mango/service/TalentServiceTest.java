package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.AnswerRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.QuestionRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.AnswerResponse;
import com.mangalaxy.mango.domain.dto.response.QuestionResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Answer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Question;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.AnswerRepository;
import com.mangalaxy.mango.repository.QuestionRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TalentServiceTest {

  @Autowired
  private TalentService talentService;

  @Autowired
  private ModelMapper modelMapper;

  @MockBean
  private TalentRepository talentRepository;

  @MockBean
  private QuestionRepository questionRepository;

  @MockBean
  private AnswerRepository answerRepository;

  private static Talent firstMockTalent = new Talent();
  private static Talent secondMockTalent = new Talent();
  private Question firstMockQuestion;
  private Question secondMockQuestion;

  @Before
  public void setUp() {

    Location location = new Location();
    location.setId(1);
    location.setCity("Kyiv");
    location.setCountry("UA");


    firstMockTalent = new Talent();
    firstMockTalent.setId(1L);
    firstMockTalent.setEmail("test@gmai.com");
    firstMockTalent.setPassword("123456");
    firstMockTalent.setFullName("Ilon Mask");
    firstMockTalent.setLocation(location);

    secondMockTalent = new Talent();
    secondMockTalent.setId(2L);
    secondMockTalent.setEmail("test2@gmai.com");
    secondMockTalent.setPassword("123456");
    secondMockTalent.setFullName("Leo Messi");
    secondMockTalent.setLocation(location);

    Profile profile = new Profile();
    profile.setId(1L);
    profile.setOwner(firstMockTalent);

    firstMockQuestion = new Question();
    firstMockQuestion.setId(1L);
    firstMockQuestion.setTalent(firstMockTalent);
    firstMockQuestion.setMessage("Some question 1");

    secondMockQuestion = new Question();
    secondMockQuestion.setId(2L);
    secondMockQuestion.setTalent(firstMockTalent);
    secondMockQuestion.setMessage("Some question 1");

  }

  @Test
  public void getTalentByIdTest() throws ResourceNotFoundException {
    Long expectedId = 1L;
    String expectedEmail = "test@gmai.com";

    Mockito.when(talentRepository.findById(expectedId)).thenReturn(Optional.of(firstMockTalent));
    TalentResponse talent = talentService.getTalentById(expectedId);
    verify(talentRepository).findById(expectedId);

    Assert.assertEquals(expectedEmail, talent.getEmail());
  }

  @Test
  public void getAllTalentsTest() throws ResourceNotFoundException {
    int expectedSize = 2;

    List<Talent> talents = new ArrayList<>();
    talents.add(firstMockTalent);
    talents.add(secondMockTalent);

    Pageable pageable = mock(Pageable.class);

    Page<Talent> talentList = new PageImpl(talents);

    Mockito.when(talentRepository.findAll(pageable)).thenReturn(talentList);

    Page<TalentResponse> allTalents = talentService.findAll(pageable);
    verify(talentRepository).findAll(pageable);

    Assert.assertEquals(expectedSize, allTalents.getContent().size());
    Assert.assertEquals(firstMockTalent.getEmail(), allTalents.getContent().get(0).getEmail());
  }

  @Test
  public void createTalentTest() {
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);
    LocationRequest locationRequest = LocationRequest.builder()
        .id(1L)
        .country("UA")
        .city("Kyiv")
        .build();
    TalentRequest talentRequest = TalentRequest.builder()
        .id(1L)
        .password("123456")
        .email("test@gmai.com")
        .fullName("Ilon Mask")
        .location(locationRequest)
        .build();
    talentService.createNewTalent(talentRequest);
    verify(talentRepository).save(firstMockTalent);
  }

  @Test
  public void updateTalentTest() throws ResourceNotFoundException {
    firstMockTalent.setEmail("new-mail@gmail.com");
    LocationRequest locationRequest = LocationRequest.builder()
        .id(1L)
        .country("UA")
        .city("Kyiv")
        .build();
    TalentRequest talentRequest = TalentRequest.builder()
        .id(1L)
        .password("123456")
        .email("new-mail@gmail.com")
        .fullName("Ilon Mask")
        .location(locationRequest)
        .build();

    Mockito.when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);

    TalentResponse updatedTalent = talentService.updateTalent(talentRequest, 1L);

    verify(talentRepository).save(firstMockTalent);
    Assert.assertEquals("new-mail@gmail.com", updatedTalent.getEmail());
  }

  @Test
  public void deleteTalentTest() throws ResourceNotFoundException {
    when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    talentService.deleteTalent(1L);
    verify(talentRepository, times(1)).delete(firstMockTalent);
  }

  @Test
  public void shouldGetTalentQuestions() throws ResourceNotFoundException {
    int expectedSize = 2;

    List<Question> questions = new ArrayList<>();
    questions.add(firstMockQuestion);
    questions.add(secondMockQuestion);

    Pageable pageable = mock(Pageable.class);
    Page<Question> page = new PageImpl<>(questions);

    Mockito.when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    Mockito.when(questionRepository.findAllByTalent(firstMockTalent, pageable)).thenReturn(page);

    Page<QuestionResponse> chat = talentService.getTalentQuestions(1L, pageable);

    Mockito.verify(talentRepository).findById(1L);
    Mockito.verify(questionRepository).findAllByTalent(firstMockTalent, pageable);
    Assert.assertEquals(expectedSize, chat.getContent().size());
  }

  @Test
  public void shouldCreateQuestionForTalent() throws ResourceNotFoundException {
    Long expectedId = 3L;
    String expectedText = "Some text";

    Question question = new Question();
    question.setId(expectedId);
    question.setMessage(expectedText);
    question.setTalent(firstMockTalent);

    Mockito.when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    Mockito.when(questionRepository.save(question)).thenReturn(question);

    QuestionResponse request = talentService.createQuestionForTalent(1L, expectedText);

    Mockito.verify(talentRepository).findById(1L);
    Mockito.verify(questionRepository).save(question);
    Assert.assertEquals(expectedId, request.getId());
    Assert.assertEquals(expectedText, request.getMessage());
  }

  @Test
  public void shouldCreateAnswerForQuestion() throws ResourceNotFoundException {
    Long expectedId = 3L;
    Long questionId = 1L;
    String expectedText = "Some Answer";

    Answer answer = new Answer();
    answer.setId(expectedId);
    answer.setMessage(expectedText);

    Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(firstMockQuestion));
    Mockito.when(answerRepository.save(answer)).thenReturn(answer);

    AnswerResponse response = talentService.createAnswerForQuestion(questionId, expectedText);

    Mockito.verify(questionRepository).findById(1L);
    Mockito.verify(answerRepository).save(answer);
    Assert.assertEquals(expectedId, response.getId());
  }
}
