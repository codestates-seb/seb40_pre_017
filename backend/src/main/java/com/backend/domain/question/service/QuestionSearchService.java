package com.backend.domain.question.service;

import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.dto.response.QuestionResponse;
import com.backend.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.tag.exception.ContainsNotExistentTags;
import com.backend.domain.tag.service.TagService;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.backend.domain.question.domain.QQuestion.question;
import static com.backend.domain.tag.domain.QTag.tag;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class QuestionSearchService {

	private final QuestionRepository questionRepository;
	private final TagService tagService;
	private final MemberRepository memberRepository;

	/**
	 *  질문 + 질문의 멤버 + 답변
	 *
	 */
	public MultiResponse<?> getList(PageRequest pageable, QuestionSearch questionSearch) {

		//        log.info("questionFindLIst= {}", questionRepository.findList(pageable,questionSearch).size());
		/* 키워드랑 태그가 달린 게시글 번호 가져오기*/
		List<Long> tagIds = questionRepository.findByTagNames(questionSearch.getTagNames());

		if (questionSearch.getTagNames().size() != tagIds.size()) {
			throw new ContainsNotExistentTags();
		}

		List<Long> questionIdHasKeywordAndTag = questionRepository.findQuestionIdBySearch(questionSearch, tagIds);
		log.info("questionIdHasKeywordAndTag={}", questionIdHasKeywordAndTag);

		/* 저 번호로 싹다 가져와서 페이징 */
		List<Tuple> questionTags = questionRepository.PageFindQuestionTags(pageable, questionSearch,
			questionIdHasKeywordAndTag);

		Map<Long, List<String>> questionTagMap = questionTags.stream().collect(
			groupingBy(tuple -> tuple.get(question.id),
				mapping(tuple -> tuple.get(tag.name), toList())));

		PageImpl<QuestionResponse> questionResponses = new PageImpl<>(
			questionRepository.findList(pageable, questionSearch, questionIdHasKeywordAndTag)
				.stream()
				.map(questionTuple ->
					QuestionResponse.builder()
						.member(MemberResponse.toResponse(questionTuple.get(question).getMember()))
						.question(SimpleQuestionResponse.toSummaryResponse(
							Objects.requireNonNull(questionTuple.get(question)),
							questionTuple.get(question.answers.size())))
						.tags(questionTagMap.get(questionTuple.get(question).getId()))
						.build()
				)
				.collect(toList()), pageable.of(), questionRepository.getCount());

		log.info("getCount = {}", questionRepository.getCount());

		return MultiResponse.of(questionResponses);
	}

}
