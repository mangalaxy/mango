package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
