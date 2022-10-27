package com.backend.domain.question.repository;

import com.backend.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    boolean existsByTitle(String title);
}
