package com.backend.domain.question.repository;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.exception.NoSuchElement;
import com.backend.global.dto.request.PageRequest;
import com.backend.global.dto.request.PageRequest.Filter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.domain.answer.domain.QAnswer.answer;
import static com.backend.domain.comment.domain.QAnswerComment.answerComment;
import static com.backend.domain.comment.domain.QQuestionComment.questionComment;
import static com.backend.domain.question.domain.QQuestion.question;
import static com.backend.domain.question.domain.QQuestionTag.questionTag;
import static com.backend.domain.tag.domain.QTag.tag;
import static com.backend.global.dto.request.PageRequest.Filter.NoAcceptedAnswer;
import static com.backend.global.dto.request.PageRequest.Filter.NoAnswer;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Tuple> findList(PageRequest pageable) {

		return jpaQueryFactory
			.select(question, question.answers.size())
			.from(question)
			.leftJoin(question.member)
			.fetchJoin()
			.where(pageable.filtering())
			.limit(pageable.getSize())
			.offset(pageable.getOffset())
			.orderBy(question.id.desc())
			.fetch();

	}

	@Override
	public Long pagingCount(PageRequest pageable) {

		return jpaQueryFactory
			.select(question.id.count())
			.from(question)
			.where(pageable.filtering())
			.fetchOne();

	}

	/* 검색 전체 조회 페이징 */
	@Override
	public List<Tuple> findList(PageRequest pageable, QuestionSearch questionSearch,
		List<Long> questionIdHasKeywordAndTag) {

		return jpaQueryFactory
			.select(question, question.answers.size())
			.from(question)
			.where(question.title.contains(questionSearch.getQuery()))
			.where(question.id.in(questionIdHasKeywordAndTag))
			.leftJoin(question.member)
			.fetchJoin()
			.limit(pageable.getSize())
			.offset(pageable.getOffset())
			.orderBy(question.id.desc())
			.fetch();
	}

	@Override
	public Long searchPagingCount(PageRequest pageable, QuestionSearch questionSearch,
		List<Long> questionIdHasKeywordAndTag) {

		return jpaQueryFactory
			.select(question.id.count())
			.from(question)
			.where(question.title.contains(questionSearch.getQuery()))
			.where(question.id.in(questionIdHasKeywordAndTag))
			.fetchOne();

	}

	/* 상세 조회 - 질문 */
	@Override
	public List<Question> findQuestionWithMemberWithQuestionComments(Long id) {

		return jpaQueryFactory.select(question)
			.from(question)
			.where(question.id.eq(id))
			.leftJoin(question.member).fetchJoin()
			.leftJoin(question.questionComments, questionComment).fetchJoin()
			.leftJoin(questionComment.member).fetchJoin()
			.fetch();

	}



}
