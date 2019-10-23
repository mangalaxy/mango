package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Question;
import com.mangalaxy.mango.domain.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
  Page<Question> findAllByTalent(Talent talent, Pageable pageable);
}
