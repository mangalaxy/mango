package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.dto.request.TalentRequest;
import com.mangalaxy.mango.dto.response.ProfileResponse;
import com.mangalaxy.mango.dto.response.TalentResponse;
import com.mangalaxy.mango.facade.TalentFacade;
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
public class TalentController {

  private TalentFacade talentFacade;

  @Autowired
  public TalentController(TalentFacade talentFacade) {
    this.talentFacade = talentFacade;
  }

  @GetMapping("{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId) {
    return new ResponseEntity<>(talentFacade.getById(talentId), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) {
    return new ResponseEntity<>(talentFacade.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("{talentId}/profile")
  public ResponseEntity<ProfileResponse> getProfileByOwner(@PathVariable Long talentId) {
    return new ResponseEntity<>(talentFacade.findProfileByOwner(talentId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    return new ResponseEntity<>(talentFacade.create(talentRequest), HttpStatus.OK);
  }

  @PutMapping("{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@RequestBody TalentRequest talentRequest, @PathVariable Long talentId) {
    return new ResponseEntity<>(talentFacade.update(talentRequest, talentId), HttpStatus.OK);
  }

  @DeleteMapping("{talentId}")
  public ResponseEntity<TalentResponse> deleteTalent(@PathVariable Long talentId) {
    return new ResponseEntity<>(talentFacade.delete(talentId), HttpStatus.OK);
  }

}
