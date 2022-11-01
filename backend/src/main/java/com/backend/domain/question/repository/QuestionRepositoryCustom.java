package com.backend.domain.question.repository;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositoryCustom {

    //전체 조회 - 페이징, 페이징+검색조건
    List<Tuple> findList(PageRequest pageable);
    List<Question> findList(PageRequest pageable,QuestionSearch questionSearch);

    //태그 디렉토리로 빼야함
    List<Tuple> PageFindQuestionTags(PageRequest pageable);
    List<Tuple> PageFindQuestionTags(PageRequest pageable,QuestionSearch questionSearch);

    //상세 조회
    List<Question> findQuestionWithMemberWithQuestionComments(Long id);
    List<Answer> findAnswersWithAnswerComment(Long id);
    List<String> findTagsOfQuestion(Long id);







}
