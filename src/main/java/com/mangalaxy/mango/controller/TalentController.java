package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Talent;
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
@RequestMapping("/api/v1/talents")
@RequiredArgsConstructor
public class TalentController {

  private final TalentService talentService;

  @GetMapping("{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId) {
    TalentResponse talent = talentService.getTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @GetMapping
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) {
    Page<TalentResponse> talents = talentService.findAll(pageable);
    return ResponseEntity.ok(talents);
  }

  @PostMapping
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    return new ResponseEntity<>(talent, HttpStatus.CREATED);
  }

  @PutMapping("{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@RequestBody TalentRequest talentRequest, @PathVariable Long talentId) {
    TalentResponse response = talentService.updateTalent(talentRequest, talentId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{talentId}")
  public ResponseEntity<Void> deleteTalent(@PathVariable Long talentId) {
    talentService.deleteTalent(talentId);
    return ResponseEntity.notFound().build();
  }
}
