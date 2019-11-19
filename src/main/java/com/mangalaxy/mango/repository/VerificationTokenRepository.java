package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository
    extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);

}
