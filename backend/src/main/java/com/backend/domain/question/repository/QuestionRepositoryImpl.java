package com.backend.domain.question.repository;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.exception.NoSuchElement;
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

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements  QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;




    @Override
    public List<Tuple> findList(PageRequest pageable) {
        List<Tuple> fetch = jpaQueryFactory
                .select(question, question.answers.size(),question.upVotes.size().subtract(question.downVotes.size()))
                .from(question)
                .groupBy(question.id)
//                .where(question.title.contains(questionSearch.getQuery()))
                .leftJoin(question.member)
                .fetchJoin()
//                .leftJoin(question.answers)
//                .fetchJoin()
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(question.id.asc())
                .fetch();
        return fetch;


    }

    @Override
    public List<Question> findList(PageRequest pageable, QuestionSearch questionSearch) {
        List<Question> questions = jpaQueryFactory
                .selectFrom(question)
                .where(question.title.contains(questionSearch.getQuery()))
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
    public  List<Question> findQuestionWithMemberWithQuestionComments(Long id) {
        return
               jpaQueryFactory.selectFrom(question)
                        .where(question.id.eq(id))
                        .leftJoin(question.member).fetchJoin()
                        .leftJoin(question.questionComments).fetchJoin()
                        .fetch();

    }

    @Override
    public List<Answer> findAnswersWithAnswerComment(Long id) {


        return jpaQueryFactory.select(answer)
                        .from(answer)
                        .leftJoin(answer.member).fetchJoin()
                        .leftJoin(answer.answerComments).fetchJoin()
                        .where(answer.question.id.eq(id))
                        .fetch();



    }


    @Override
    public List<Tuple> PageFindQuestionTags(PageRequest pageable) {

        // 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수
        List<Tuple> tagSizeQuery = countTagSizeForLimit(pageable);

        ifSizeZeroThrowNoSuchElementException(tagSizeQuery);

        long tagSize = tagSizeQuery.stream().mapToLong(value -> value.get(questionTag.id.count())).sum();
        Long firstId = tagSizeQuery.get(0).get(question.id);

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

    private void ifSizeZeroThrowNoSuchElementException(List<Tuple> tagSizeQuery) {
        if (tagSizeQuery.size()==0) {
            throw new NoSuchElement();
        }
    }

    @Override
    public List<Tuple> PageFindQuestionTags(PageRequest pageable, QuestionSearch questionSearch) {
        // 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수
        List<Tuple> tagSizeQuery = countTagSizeForLimit(pageable, questionSearch);

        ifSizeZeroThrowNoSuchElementException(tagSizeQuery);

        long tagSize = tagSizeQuery.stream().mapToLong(value -> value.get(questionTag.id.count())).sum();

        Long firstId = tagSizeQuery.get(0).get(question.id);

        log.info("tagSize = {}",tagSize);

        return jpaQueryFactory.select(question.id,tag.name)
                .from(question)
                .where(question.title.contains(questionSearch.getQuery()))
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


    @Override
    public List<String> findTagsOfQuestion(Long id) {

        log.info("findTagsOfQuestion");
        return jpaQueryFactory.select(tag.name)
                .from(question)
                .leftJoin(questionTag)
                .on(question.eq(questionTag.question))
                .leftJoin(tag)
                .on(questionTag.tag.eq(tag))
                .where(question.id.eq(id))
                .fetch();




    }

    public List<Tuple> countTagSizeForLimit(PageRequest pageable){
        return jpaQueryFactory.select(question.id,questionTag.id.count())
                .from(questionTag)
                .offset(pageable.getOffset())
                .limit(pageable.getSize())
                .groupBy(question.id)
                .fetch();

    }



    public List<Tuple> countTagSizeForLimit(PageRequest pageable,QuestionSearch questionSearch){
        return jpaQueryFactory.select(question.id,questionTag.id.count())
                .from(questionTag)
                .where(questionTag.question.title.contains(questionSearch.getQuery()))
                .offset(pageable.getOffset())
                .limit(pageable.getSize())
                .groupBy(question.id)
                .fetch();

    }


}
