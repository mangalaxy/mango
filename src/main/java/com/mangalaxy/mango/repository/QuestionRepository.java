package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.QuestionMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionMessage, Long> {
}
