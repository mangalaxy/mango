package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
