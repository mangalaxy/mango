package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.AnswerRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.AnswerResponse;
import com.mangalaxy.mango.domain.dto.response.QuestionResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.TalentService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Api(value = "Talent Data API", description = "List of methods that manage talents")
public class TalentController {

  private final TalentService talentService;

  @GetMapping("talents/{talentId}")
  @ApiOperation(value = "Get talent by id")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Talent with given id not found")})
  public ResponseEntity<TalentResponse> getTalentById(
      @ApiParam(value = "Talent id from which talent object will retrieve", required = true)
      @PathVariable Long talentId) throws ResourceNotFoundException {
    TalentResponse talent = talentService.getTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @GetMapping("talents")
  @ApiOperation(value = "Get list of Talents")
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) throws ResourceNotFoundException {
    Page<TalentResponse> talents = talentService.findAll(pageable);
    return ResponseEntity.ok(talents);
  }

  @PostMapping("talents")
  @ApiOperation(value = "Create new Talent")
  public ResponseEntity<TalentResponse> createTalent(
      @ApiParam(value = "Talent object store in database table", required = true)
      @RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    return new ResponseEntity<>(talent, HttpStatus.CREATED);
  }

  @PutMapping("talents/{talentId}")
  @ApiOperation(value = "Update talent")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Talent with given id not found")})
  public ResponseEntity<TalentResponse> updateTalent(
      @ApiParam(value = "Update talent object", required = true)
      @RequestBody TalentRequest talentRequest,
      @ApiParam(value = "Talent Id to update talent object", required = true)
      @PathVariable Long talentId) throws ResourceNotFoundException {
    TalentResponse response = talentService.updateTalent(talentRequest, talentId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("talents/{talentId}")
  @ApiOperation(value = "Delete talent")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Talent with given id not found")})
  public ResponseEntity<Void> deleteTalent(
      @ApiParam(value = "Talent Id from which talent object will delete from database table", required = true)
      @PathVariable Long talentId) throws ResourceNotFoundException {
    talentService.deleteTalent(talentId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("me")
  @ApiOperation(value = "Get authenticated talent")
  public ResponseEntity<TalentResponse> getCurrentTalent() {
    TalentResponse talentResponse = talentService.getCurrentTalent();
    return ResponseEntity.ok(talentResponse);
  }

  @PutMapping("me")
  @ApiOperation(value = "Update authenticated talent")
  public ResponseEntity<TalentResponse> updateCurrentTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talentResponse = talentService.updateCurrentTalent(talentRequest);
    return ResponseEntity.ok(talentResponse);
  }

  @DeleteMapping("me")
  @ApiOperation(value = "Delete authenticated talent")
  public ResponseEntity<Void> deleteCurrentTalent() {
    talentService.deleteCurrentTalent();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("talents/{id}/chat")
  public ResponseEntity<Page<QuestionResponse>> getTalentQuestions(@PathVariable Long id, Pageable pageable) throws ResourceNotFoundException {
    Page<QuestionResponse> chat = talentService.getTalentQuestions(id, pageable);
    return ResponseEntity.ok(chat);
  }

  @PostMapping("talents/{id}/chat")
  public ResponseEntity<QuestionResponse> createQuestionForTalent(@PathVariable Long id, @RequestBody String text) throws ResourceNotFoundException {
    QuestionResponse response = talentService.createQuestionForTalent(id, text);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("talents/{id}/chat/questions/{questionId}/answers")
  public ResponseEntity<AnswerResponse> createAnswerForQuestion(@PathVariable Long questionId, @RequestBody String message) throws ResourceNotFoundException {
    AnswerResponse response = talentService.createAnswerForQuestion(questionId, message);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
