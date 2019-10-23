package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.AnswerRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.AnswerResponse;
import com.mangalaxy.mango.domain.dto.response.QuestionResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Answer;
import com.mangalaxy.mango.domain.entity.Question;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.AnswerRepository;
import com.mangalaxy.mango.repository.QuestionRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TalentServiceImpl implements TalentService{

  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  @Override
  public Talent getPrincipalTalent() {
    Talent currentTalent = talentRepository.findAll().get(0);
    return currentTalent;
  }

  @Override
  public Page<QuestionResponse> getTalentQuestions(Long talentId, Pageable pageable) throws ResourceNotFoundException {
    Talent talentFromDb = talentRepository.findById(talentId).orElseThrow(ResourceNotFoundException::new);
    Page<Question> questions = questionRepository.findAllByTalent(talentFromDb, pageable);
    Page<QuestionResponse> chat = questions.map(question -> modelMapper.map(question, QuestionResponse.class));
    return chat;
  }

  @Override
  public QuestionResponse createQuestionForTalent(Long talentId, String text) throws ResourceNotFoundException {
    Talent talent = talentRepository.findById(talentId).orElseThrow(ResourceNotFoundException::new);
    Question question = new Question();
    question.setTalent(talent);
    question.setMessage(text);
    Question savedQuestion = questionRepository.save(question);
    return modelMapper.map(savedQuestion, QuestionResponse.class);
  }

  @Override
  public AnswerResponse createAnswerForQuestion(Long questionId, String message) throws ResourceNotFoundException {
    Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
    Answer answer = new Answer();
    answer.setMessage(message);
    answer.setQuestion(question);
    Answer savedAnswer = answerRepository.save(answer);
    question.setAnswer(savedAnswer);
    questionRepository.save(question);
    return modelMapper.map(savedAnswer, AnswerResponse.class);
  }

  @Override
  public Page<TalentResponse> findAll(Pageable pageable) throws ResourceNotFoundException {
    Page<Talent> talents = talentRepository.findAll(pageable);
    if (talents.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    Page<TalentResponse> response = talents.map(talent -> modelMapper.map(talent, TalentResponse.class));
    return response;
  }

  @Override
  public TalentResponse getTalentById(Long id) throws ResourceNotFoundException {
    Talent talent = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return modelMapper.map(talent, TalentResponse.class);
  }

  @Override
  public TalentResponse createNewTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent createdTalent = talentRepository.save(talent);
    return modelMapper.map(createdTalent, TalentResponse.class);
  }

  @Override
  public TalentResponse updateTalent(TalentRequest talentRequest, Long id) throws ResourceNotFoundException {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent talentFromDb = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    talent.setId(talentFromDb.getId());
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Override
  public void deleteTalent(Long id) throws ResourceNotFoundException {
    Talent talent = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    talentRepository.delete(talent);
  }

  @Override
  public TalentResponse getCurrentTalent() {
    Talent curentTalent = getPrincipalTalent();
    return modelMapper.map(curentTalent, TalentResponse.class);
  }


  @Override
  public TalentResponse updateCurrentTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    talent.setId(getPrincipalTalent().getId());
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Override
  public void deleteCurrentTalent() {
    talentRepository.delete(getPrincipalTalent());
  }
}
