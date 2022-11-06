package com.backend.domain.tag.repository;

import static com.backend.domain.question.domain.QQuestion.*;
import static com.backend.domain.question.domain.QQuestionTag.*;
import static com.backend.domain.tag.domain.QTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.exception.NoSuchElement;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QueryTagRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Tuple> pageFindQuestionTags(PageRequest pageable, QuestionSearch questionSearch,
		List<Long> questionIds) {
		/* 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수 */
		List<Tuple> tagSizeQuery = countTagSizeForLimit(pageable, questionSearch, questionIds);

		ifSizeZeroThrowNoSuchElementException(tagSizeQuery);

		long tagSize = tagSizeQuery.stream().mapToLong(value -> value.get(questionTag.id.count()))
			.sum();

		Long firstId = tagSizeQuery.get(0).get(question.id);

		log.info("tagSize = {}", tagSize);

		return jpaQueryFactory.select(question.id, tag.name)
			.from(question)
			.where(question.title.contains(questionSearch.getQuery()))
			.where(questionTag.question.id.in(questionIds))
			.leftJoin(questionTag)
			.on(question.id.eq(questionTag.question.id))
			.leftJoin(tag)
			.on(questionTag.tag.id.eq(tag.id))
			.limit(tagSize)
			.where(question.id.loe(firstId))
			.orderBy(question.id.desc(), questionTag.id.asc())
			.fetch();
	}

	public List<Tuple> pageFindQuestionTags(PageRequest pageable) {

		/* 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수 */
		List<Tuple> tagSizeQuery = countTagSizeForLimit(pageable);

		ifSizeZeroThrowNoSuchElementException(tagSizeQuery);

		Long tagSize = tagSizeQuery.stream().mapToLong(value -> value.get(questionTag.id.count()))
			.sum();
		Long firstId = tagSizeQuery.get(0).get(question.id);

		return jpaQueryFactory.select(question.id, tag.name)
			.from(question)
			.leftJoin(questionTag)
			.on(question.id.eq(questionTag.question.id))
			.leftJoin(tag)
			.on(questionTag.tag.id.eq(tag.id))
			.limit(tagSize)
			.where(question.id.loe(firstId), pageable.filtering())
			.orderBy(question.id.desc(), questionTag.id.asc())
			.fetch();
	}

	public List<Tuple> countTagSizeForLimit(PageRequest pageable) {
		return jpaQueryFactory.select(question.id, questionTag.id.count())
			.from(questionTag)
			.where(pageable.filtering())
			.offset(pageable.getOffset())
			.limit(pageable.getSize())
			.groupBy(question.id)
			.orderBy(question.id.desc())
			.fetch();
	}

	public List<Tuple> countTagSizeForLimit(PageRequest pageable, QuestionSearch questionSearch,
		List<Long> questionIds) {
		return jpaQueryFactory.select(question.id, questionTag.id.count())
			.from(questionTag)
			.where(questionTag.question.title.contains(questionSearch.getQuery()))
			.where(questionTag.question.id.in(questionIds))
			.offset(pageable.getOffset())
			.limit(pageable.getSize())
			.groupBy(question.id)
			.orderBy(question.id.desc())
			.fetch();
	}

	public List<Long> findQuestionIdBySearch(QuestionSearch questionSearch, List<Long> tagIds) {
		return jpaQueryFactory.selectDistinct(question.id)
			.from(questionTag)
			.join(question)
			.on(questionTag.question.id.eq(question.id))
			.where(question.title.contains(questionSearch.getQuery()), inTagIds(tagIds))
			.orderBy(question.id.desc())
			.fetch();
	}

	public List<String> findTagsOfQuestion(Long id) {
		return jpaQueryFactory.select(tag.name)
			.from(question)
			.leftJoin(questionTag)
			.on(question.eq(questionTag.question))
			.leftJoin(tag)
			.on(questionTag.tag.eq(tag))
			.where(question.id.eq(id))
			.fetch();
	}

	public List<Long> findByTagNames(List<String> tagNames) {
		return jpaQueryFactory.select(tag.id)
			.from(tag)
			.where(tag.name.in(tagNames))
			.fetch();
	}

	public BooleanExpression inTagIds(List<Long> tagIds) {
		return tagIds.size() == 0 ? null : questionTag.tag.id.in(tagIds);
	}

	private void ifSizeZeroThrowNoSuchElementException(List<Tuple> tagSizeQuery) {
		if (tagSizeQuery.size() == 0) {
			throw new NoSuchElement();
		}
	}
}
