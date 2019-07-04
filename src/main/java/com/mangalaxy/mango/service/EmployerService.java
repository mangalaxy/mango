package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.Employer;
import com.mangalaxy.mango.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployerService implements UserDetailsService {

  private EmployerRepository employerRepository;

  @Autowired
  public EmployerService(EmployerRepository employerRepository) {
    this.employerRepository = employerRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employer employer = employerRepository.findByWorkEmail(email).get();
    return org.springframework.security.core.userdetails.User.builder()
        .username(employer.getWorkEmail())
        .password(employer.getPassword())
        .authorities(employer.getRoles())
        .build();
  }
}
