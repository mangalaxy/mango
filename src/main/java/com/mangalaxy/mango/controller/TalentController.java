package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.ProfileResponse;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
import com.mangalaxy.mango.mapper.TalentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/talents")
@RequiredArgsConstructor
public class TalentController {

  private final TalentMapper talentMapper;

  @GetMapping("{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId) {
    ResponseEntity<TalentResponse> response = new ResponseEntity<>(talentMapper.getById(talentId), HttpStatus.OK);
    return response;
  }

  @GetMapping
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) {
    ResponseEntity<Page<TalentResponse>> response = new ResponseEntity<>(talentMapper.findAll(pageable), HttpStatus.OK);
    return response;
  }

  @GetMapping("{talentId}/profile")
  public ResponseEntity<ProfileResponse> getProfileByOwner(@PathVariable Long talentId) {
    ResponseEntity<ProfileResponse> response = new ResponseEntity<>(talentMapper.findProfileByOwner(talentId), HttpStatus.OK);
    return response;
  }

  @PostMapping
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    ResponseEntity<TalentResponse> response = new ResponseEntity<>(talentMapper.create(talentRequest), HttpStatus.CREATED);
    return response;
  }

  @PutMapping("{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@RequestBody TalentRequest talentRequest, @PathVariable Long talentId) {
    ResponseEntity<TalentResponse> response = new ResponseEntity<>(talentMapper.update(talentRequest, talentId), HttpStatus.OK);
    return response;
  }

  @DeleteMapping("{talentId}")
  public ResponseEntity<Void> deleteTalent(@PathVariable Long talentId) {
    if (talentMapper.delete(talentId) != null) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
