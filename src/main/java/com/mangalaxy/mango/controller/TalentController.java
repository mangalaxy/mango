package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

  @PreAuthorize("hasAuthority('TALENT')")
  @PostMapping("talents")
  @ApiOperation(value = "Create new Talent")
  public ResponseEntity<TalentResponse> createTalent(
      @ApiParam(value = "Talent object store in database table", required = true)
      @RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    return new ResponseEntity<>(talent, HttpStatus.CREATED);
  }

  @PreAuthorize("hasAuthority('TALENT')")
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

  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("talents/{talentId}")
  @ApiOperation(value = "Delete talent")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Talent with given id not found")})
  public ResponseEntity<Void> deleteTalent(
      @ApiParam(value = "Talent Id from which talent object will delete from database table", required = true)
      @PathVariable Long talentId) throws ResourceNotFoundException {
    talentService.deleteTalent(talentId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @GetMapping("me")
  @ApiOperation(value = "Get authenticated talent")
  public ResponseEntity<TalentResponse> getCurrentTalent() {
    TalentResponse talentResponse = talentService.getCurrentTalent();
    return ResponseEntity.ok(talentResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("me")
  @ApiOperation(value = "Update authenticated talent")
  public ResponseEntity<TalentResponse> updateCurrentTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talentResponse = talentService.updateCurrentTalent(talentRequest);
    return ResponseEntity.ok(talentResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("me")
  @ApiOperation(value = "Delete authenticated talent")
  public ResponseEntity<Void> deleteCurrentTalent() {
    talentService.deleteCurrentTalent();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
