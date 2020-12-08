package com.mangalaxy.mango.security;

import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final TalentRepository talentRepository;
  private final EmployerRepository employerRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) {
    Optional<Talent> talentOpt = talentRepository.findByEmail(email);
    if (talentOpt.isPresent()) {
      return UserPrincipal.from(talentOpt.get());
    } else {
      Optional<Employer> employerOpt = employerRepository.findByEmail(email);
      if (employerOpt.isPresent()) {
        return UserPrincipal.from(employerOpt.get());
      } else {
        throw new UsernameNotFoundException("User not found with provided email: " + email);
      }
    }
  }

}
