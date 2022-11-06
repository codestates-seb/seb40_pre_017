package com.backend.domain.answer.dao;

import static com.backend.domain.answer.domain.QAnswer.*;
import static com.backend.domain.comment.domain.QAnswerComment.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.backend.domain.answer.domain.Answer;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryAnswerRepository {

	private final JPAQueryFactory jpaQueryFactory;

	/* 상세 조회 - 답변 */
	public List<Answer> findAnswersWithAnswerComment(Long id) {
		return jpaQueryFactory.select(answer)
			.from(answer)
			.leftJoin(answer.member).fetchJoin()
			.leftJoin(answer.answerComments, answerComment).fetchJoin()
			.leftJoin(answerComment.member).fetchJoin()
			.where(answer.question.id.eq(id))
			.fetch();

	}

}
