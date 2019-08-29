package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.TalentService;
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

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TalentController {

  private final TalentService talentService;

  @GetMapping("talents/{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId) {
    TalentResponse talent = talentService.getTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @GetMapping("talents")
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) {
    Page<TalentResponse> talents = talentService.findAll(pageable);
    return ResponseEntity.ok(talents);
  }

  @PostMapping("talents")
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    return new ResponseEntity<>(talent, HttpStatus.CREATED);
  }

  @PutMapping("talents/{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@RequestBody TalentRequest talentRequest, @PathVariable Long talentId) {
    TalentResponse response = talentService.updateTalent(talentRequest, talentId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("talents/{talentId}")
  public ResponseEntity<Void> deleteTalent(@PathVariable Long talentId) {
    talentService.deleteTalent(talentId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("me")
  public ResponseEntity<TalentResponse> getCurrentTalent() {
    TalentResponse talentResponse = talentService.getCurrentTalent();
    return ResponseEntity.ok(talentResponse);
  }

  @PutMapping("me")
  public ResponseEntity<TalentResponse> updateCurrentTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talentResponse = talentService.updateCurrentTalent(talentRequest);
    return ResponseEntity.ok(talentResponse);
  }

  @DeleteMapping("me")
  public ResponseEntity<Void> deleteCurrentTalent() {
    talentService.deleteCurrentTalent();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
