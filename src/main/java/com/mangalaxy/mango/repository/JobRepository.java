package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
