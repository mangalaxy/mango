package com.mangalaxy.mango.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents full information about working company.
 *
 * @see Employer
 * @see Job
 */
@Data
public class Company {

  private Long id;
  private String name;
  private String logoUrl;
  private String headline;
  private String address;
  private String size;
  private String industry;
  private String mediaUrl;
  private String aboutText;
  private List<Skill> techStack = new ArrayList<>();
  private List<String> perks = new ArrayList<>();
  private List<String> benefits = new ArrayList<>();
  private List<String> links = new ArrayList<>();
  private List<String> gallery = new ArrayList<>();
  private Set<Employer> recruiters = new HashSet<>();
  private LocalDate createdOn;
  private LocalDate updatedOn;
  private Employer updatedBy;
}
