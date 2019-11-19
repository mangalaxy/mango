package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail (String email);
}
