package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
