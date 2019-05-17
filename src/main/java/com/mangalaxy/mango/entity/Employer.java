package com.mangalaxy.mango.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Employer {
  private Long id;
  private String fullName;
  private String email;
  private String password;
  private String phoneNumber;
  private Company company;
  private String jobTitle;
  private String photoUrl;
  private Location location;
  private Set<Job> openJobs = new HashSet<>();
  private LocalDate registeredOn;
  private LocalDate updatedOn;
}
