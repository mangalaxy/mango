package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.PasswordResetToken;
import org.springframework.stereotype.Repository;

// TODO: Refactor this
@Repository
public interface PasswordResetTokenRepository {

  PasswordResetToken findByToken(String token);

}
