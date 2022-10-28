package com.backend.domain.question.repository;

import com.backend.domain.answer.domain.QAnswer;
import com.backend.domain.member.domain.QMember;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.domain.answer.domain.QAnswer.answer;
import static com.backend.domain.member.domain.QMember.*;
import static com.backend.domain.question.domain.QQuestion.*;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements  QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    /* 아직 최적화 안됨 */

    @Override
    public List<Question> getList(PageRequest pageable, QuestionSearch questionSearch) {
        List<Question> questions = jpaQueryFactory
                .selectFrom(question)
                .leftJoin(question.member, member)
                .fetchJoin()
                .leftJoin(question.answers, answer)
                .fetchJoin()
                .leftJoin(question.questionTags)
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(question.id.asc())
                .fetch();
        return questions;
    }


}
