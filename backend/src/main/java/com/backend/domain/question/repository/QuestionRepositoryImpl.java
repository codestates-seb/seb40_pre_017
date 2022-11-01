package com.backend.domain.question.repository;

import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.backend.domain.answer.domain.QAnswer.answer;
import static com.backend.domain.member.domain.QMember.*;
import static com.backend.domain.question.domain.QQuestion.*;
import static com.backend.domain.question.domain.QQuestionTag.*;
import static com.backend.domain.tag.domain.QTag.tag;
import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;
import static com.backend.domain.vote.domain.QQuestionUpVote.questionUpVote;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements  QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    /* 아직 최적화 안됨 */

    @Override
    public List<Question> findList(PageRequest pageable, QuestionSearch questionSearch) {
        List<Question> questions = jpaQueryFactory
                .selectFrom(question)
                .leftJoin(question.member, member)
                .fetchJoin()
                .leftJoin(question.answers, answer)
                .fetchJoin()
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(question.id.asc())
                .fetch();
        return questions;
    }

    @Override
    public Optional<Question> findQuestionWithMemberWithAnswers(Long id) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(question)
                .where(question.id.eq(id))
                .leftJoin(question.member).fetchJoin()
                .leftJoin(question.answers).fetchJoin()
                .fetchOne()
        );


    }

    @Override
    public List<Tuple> findQuestionTags(PageRequest pageable) {


        // 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수
        List<Tuple> fetch = jpaQueryFactory.select(question.id,questionTag.id.count())
                .from(questionTag)
                .offset(pageable.getOffset())
                .limit(pageable.getSize())
                .groupBy(question.id)
                .fetch();

        long tagSize = fetch.stream().mapToLong(value -> value.get(questionTag.id.count())).sum();
        Long firstId = fetch.get(0).get(question.id);


        log.info("tagSize = {}",tagSize);


        return jpaQueryFactory.select(question.id,tag.name)
                .from(question)
                .leftJoin(questionTag)
                .on(question.id.eq(questionTag.question.id))
                .fetchJoin()
                .leftJoin(tag)
                .on(questionTag.tag.id.eq(tag.id))
                .fetchJoin()
                .limit(tagSize)
                .where(question.id.goe(firstId))
                .orderBy(question.id.asc())
                .fetch();
    }


}
