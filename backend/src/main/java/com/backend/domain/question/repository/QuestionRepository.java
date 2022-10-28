package com.backend.domain.question.repository;

import com.backend.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import static com.backend.domain.question.domain.QQuestion.question;

public interface QuestionRepository extends JpaRepository<Question,Long>, QuestionRepositoryCustom {
    boolean existsByTitle(String title);

    @Query("select count(q) from Question q")
    Long getCount();


}
