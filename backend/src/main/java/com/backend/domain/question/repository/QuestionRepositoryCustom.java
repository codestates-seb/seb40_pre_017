package com.backend.domain.question.repository;

import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.global.dto.request.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepositoryCustom {
    List<Question> getList(PageRequest pageable, QuestionSearch questionSearch);
    Optional<Question> getQuestionWithMemberWithAnswers(Long id);

}
