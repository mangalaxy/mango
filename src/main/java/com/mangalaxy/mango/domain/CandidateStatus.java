package com.mangalaxy.mango.domain;


/**
 * Determines candidate status with respect to employers.
 *
 * @author Yuri Podolsky
 */
public enum CandidateStatus {
  SEARCHING("I'm looking for a new job"),
  OFFERING("Open for new job offers"),
  EMPLOYED("Currently, I'm not available for offers");

  private final String definition;

  CandidateStatus(String definition) {
    this.definition = definition;
  }

  public String getDefinition() {
    return definition;
  }

}
