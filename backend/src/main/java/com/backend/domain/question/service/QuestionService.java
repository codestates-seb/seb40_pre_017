package com.backend.domain.question.service;

import com.backend.domain.answer.dto.ComplexAnswerResponse;
import com.backend.domain.comment.dto.SimpleAnswerCommentResponse;
import com.backend.domain.comment.dto.SimpleQuestionCommentResponse;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.global.repository.MemberRepository;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.domain.QuestionTag;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.question.dto.response.DetailQuestionResponse;
import com.backend.domain.question.dto.response.QuestionResponse;
import com.backend.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.exception.TitleDuplication;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.tag.domain.Tag;
import com.backend.domain.tag.dto.TagDto;
import com.backend.domain.tag.service.TagService;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.backend.domain.question.domain.QQuestion.question;
import static com.backend.domain.tag.domain.QTag.tag;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TagService tagService;
    private final MemberRepository memberRepository;

    /**
     * 1. dto에서 태그를 가져온다.
     * 2. 제목이 중복인지 확인한다.
     * 3. 태그를 등록한다 ( 있는 태그면 넘어가고 새 태그면 등록한다.)
     * 4.
     */
    @Transactional
    public Long create(Long memberId, QuestionCreate questionCreate) {


        existsSameTitle(questionCreate.getTitle());
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
        List<QuestionTag> questionTags = makeQuestionTags(questionCreate.getTags());

        // 현재 사용자 가져오는 로직으로 수정 필요

        Question question = Question.createQuestion(questionCreate, member, questionTags);

        return questionRepository.save(question).getId();

    }

    @Transactional
    public DetailQuestionResponse get(Long id){

        Question question = questionRepository.findQuestionWithMemberWithAnswers(id).orElseThrow(QuestionNotFound::new);
        question.hit();

        List<SimpleQuestionCommentResponse> questionCommentResponses = question.getQuestionComments().stream()
                .map(SimpleQuestionCommentResponse::of)
                .collect(toList());

        List<ComplexAnswerResponse> complexAnswerResponses = question.getAnswers().stream()
                .map(answer ->
                        ComplexAnswerResponse.of(answer, answer.getAnswerComments().stream()
                                .map(SimpleAnswerCommentResponse::of)
                                .collect(toList())))
                .collect(toList());


        DetailQuestionResponse detailQuestionResponse = DetailQuestionResponse.of(question,complexAnswerResponses,questionCommentResponses);


    return detailQuestionResponse;
    }



    public MultiResponse<?> getList(PageRequest pageable, QuestionSearch questionSearch){

        log.info("questionFindLIst= {}", questionRepository.findList(pageable,questionSearch).size());

        List<Tuple> questionTags = questionRepository.findQuestionTags(pageable);

        Map<Long, List<String>> questionTagMap = questionTags.stream().collect(
                groupingBy(tuple -> tuple.get(question.id),
                mapping(tuple -> tuple.get(tag.name),toList())));


        PageImpl<QuestionResponse> questionResponses = new PageImpl<>(questionRepository.findList(pageable, questionSearch)
                .stream()
                .map(question -> QuestionResponse.builder()
                        .member(MemberResponse.toResponse(question.getMember()))
                        .question(SimpleQuestionResponse.toSummaryResponse(question))
                        .tags(questionTagMap.get(question.getId()))
                        .build()
                )
                .collect(toList()),pageable.of(), questionRepository.getCount());

        log.info("getCount = {}" , questionRepository.getCount());

        MultiResponse<?> multiResponse = MultiResponse.of(questionResponses);

        return multiResponse;
    }



    @Transactional
    public Long update(Long id, QuestionUpdate questionUpdate) {

        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);

        if (!Objects.equals(question.getTitle(), questionUpdate.getTitle())) {
            existsSameTitle(questionUpdate.getTitle());
        }

        List<QuestionTag> questionTags = makeQuestionTags(questionUpdate.getTags());

        question.updateQuestion(questionUpdate, questionTags);

        return id;
    }

    @Transactional
    public Long delete(Long id){
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        questionRepository.delete(question);

        return id;

    }


    /* 비즈니스 로직 경계 */

    private Member getMember() {
        Member member = Member.builder()
                .email("thwn40@naver.com")
                .password("asdf123")
                .profileImage("sdlfkjasldkfj")
                .reputation(0L)
                .username("thwn400")
                .build();
        return member;
    }

    private List<QuestionTag> makeQuestionTags(List<TagDto> questionCreate) {

        return questionCreate.stream().map(
                tagDto -> {
                    Tag tag = tagService.addTag(tagDto);
                    return QuestionTag.createQuestionTag(tag);
                }
        ).collect(toList());
    }

    private void existsSameTitle(String title) {
        if (questionRepository.existsByTitle(title)) {
            throw new TitleDuplication();
        }
    }
}
