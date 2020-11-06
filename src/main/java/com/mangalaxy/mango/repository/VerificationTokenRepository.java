package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.VerificationToken;

public interface VerificationTokenRepository
//    extends JpaRepository<VerificationToken, Long> {
{

  VerificationToken findByToken(String token);

}
