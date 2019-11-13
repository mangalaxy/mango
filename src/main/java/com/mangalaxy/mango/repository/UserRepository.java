package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail (String email);

  Boolean existsByEmail(String email);
}
