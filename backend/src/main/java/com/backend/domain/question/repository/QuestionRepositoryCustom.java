package com.backend.domain.question.repository;

import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.tag.domain.Tag;
import com.backend.global.dto.request.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepositoryCustom {
    List<Question> findList(PageRequest pageable, QuestionSearch questionSearch);
    Optional<Question> findQuestionWithMemberWithAnswers(Long id);
    List<Tag> findQuestionTags(Long id);

}
