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
import static com.backend.domain.question.domain.QQuestion.*;
import static com.backend.domain.question.domain.QQuestionTag.*;
import static com.backend.domain.tag.domain.QTag.tag;
import static com.backend.global.dto.request.PageRequest.Filter.*;
import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;
import static com.backend.domain.vote.domain.QQuestionUpVote.questionUpVote;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;


	@Override
	public List<Tuple> findList(PageRequest pageable) {

<<<<<<< HEAD
        List<Tuple> fetch = jpaQueryFactory
                .select(question, question.answers.size())
                .from(question)
                .leftJoin(question.member)
                .fetchJoin()
                .where(filtering(pageable))
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(question.id.desc())
                .fetch();
        return fetch;
=======
		List<Tuple> fetch = jpaQueryFactory
			.select(question, question.answers.size())
			.from(question)
			.leftJoin(question.member)
			.fetchJoin()
			.where(filtering(pageable))
			.limit(pageable.getSize())
			.offset(pageable.getOffset())
			.orderBy(question.id.desc())
			.fetch();
>>>>>>> dev

		return fetch;

	}

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

<<<<<<< HEAD
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
=======
	@Override
	public List<Question> findQuestionWithMemberWithQuestionComments(Long id) {
>>>>>>> dev

		return jpaQueryFactory.select(question)
			.from(question)
			.where(question.id.eq(id))
			.leftJoin(question.member).fetchJoin()
			.leftJoin(question.questionComments, questionComment).fetchJoin()
			.leftJoin(questionComment.member).fetchJoin()
			.fetch();

	}

	@Override
	public List<Answer> findAnswersWithAnswerComment(Long id) {

		return jpaQueryFactory.select(answer)
			.from(answer)
			.leftJoin(answer.member).fetchJoin()
			.leftJoin(answer.answerComments, answerComment).fetchJoin()
			.leftJoin(answerComment.member).fetchJoin()
			.where(answer.question.id.eq(id))
			.fetch();

	}

	@Override
	public List<Tuple> PageFindQuestionTags(PageRequest pageable) {
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
			.where(question.id.goe(firstId), filtering(pageable))
			.orderBy(question.id.desc())
			.fetch();
	}

	@Override
	public List<Tuple> PageFindQuestionTags(PageRequest pageable, QuestionSearch questionSearch,
		List<Long> questionIds) {
		/* 가져올 컬럼수 = 가져올 게시글의 딸린 태그 수 */
		List<Tuple> tagSizeQuery = countTagSizeForLimit(pageable, questionSearch, questionIds);

		ifSizeZeroThrowNoSuchElementException(tagSizeQuery);

		long tagSize = tagSizeQuery.stream().mapToLong(value -> value.get(questionTag.id.count()))
			.sum();

		Long firstId = tagSizeQuery.get(0).get(question.id);

		log.info("tagSize = {}", tagSize);

<<<<<<< HEAD
        return jpaQueryFactory.select(question.id, tag.name)
                .from(question)
                .leftJoin(questionTag)
                .on(question.id.eq(questionTag.question.id))
                .leftJoin(tag)
                .on(questionTag.tag.id.eq(tag.id))
                .limit(tagSize)
                .where(question.id.goe(firstId),filtering(pageable))
                .orderBy(question.id.desc())
                .fetch();
    }
=======
		return jpaQueryFactory.select(question.id, tag.name)
			.from(question)
			.where(question.title.contains(questionSearch.getQuery()))
			.where(questionTag.question.id.in(questionIds))
			.leftJoin(questionTag)
			.on(question.id.eq(questionTag.question.id))
			.leftJoin(tag)
			.on(questionTag.tag.id.eq(tag.id))
			.limit(tagSize)
			.where(question.id.goe(firstId))
			.orderBy(question.id.desc())
			.fetch();
>>>>>>> dev

	}

	@Override
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

	@Override
	public List<Long> findByTagNames(List<String> tagNames) {

		List<Long> fetch = jpaQueryFactory.select(tag.id)
			.from(tag)
			.where(tag.name.in(tagNames))
			.fetch();

		return fetch;

<<<<<<< HEAD
        return jpaQueryFactory.select(question.id, tag.name)
                .from(question)
                .where(question.title.contains(questionSearch.getQuery()))
                .where(questionTag.question.id.in(questionIds))
                .leftJoin(questionTag)
                .on(question.id.eq(questionTag.question.id))
                .leftJoin(tag)
                .on(questionTag.tag.id.eq(tag.id))
                .limit(tagSize)
                .where(question.id.goe(firstId))
                .orderBy(question.id.desc())
                .fetch();
    }
=======
	}
>>>>>>> dev

	public BooleanExpression inTagIds(List<Long> tagIds) {

		if (tagIds.size() == 0) {
			return null;
		}
		return questionTag.tag.id.in(tagIds);
	}

	@Override
	public List<Long> findQuestionIdBySearch(QuestionSearch questionSearch, List<Long> tagIds) {

		return jpaQueryFactory.selectDistinct(question.id)
			.from(questionTag)
			.join(question)
			.on(questionTag.question.id.eq(question.id))
			.where(question.title.contains(questionSearch.getQuery()), inTagIds(tagIds))
			.fetch();
	}

	public List<Tuple> countTagSizeForLimit(PageRequest pageable) {

		return jpaQueryFactory.select(question.id, questionTag.id.count())
			.from(questionTag)
			.where(filtering(pageable))
			.offset(pageable.getOffset())
			.limit(pageable.getSize())
			.groupBy(question.id)
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
			.fetch();
	}

	private BooleanBuilder filtering(PageRequest pageable) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (pageable.getFilterEnums() == null) {
			return null;
		}

		for (Filter filterEnum : pageable.getFilterEnums()) {
			if (filterEnum.equals(NoAnswer)) {
				booleanBuilder.and(question.answers.size().eq(0));
			}
			if (filterEnum.equals(NoAcceptedAnswer)) {
				booleanBuilder.and(question.isAnswered.eq(false));
			}
		}

		return booleanBuilder;
	}

	private void ifSizeZeroThrowNoSuchElementException(List<Tuple> tagSizeQuery) {
		if (tagSizeQuery.size() == 0) {
			throw new NoSuchElement();
		}
	}

}
