package com.backend.domain.question.service;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.ComplexAnswerResponse;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.comment.dto.SimpleAnswerCommentResponse;
import com.backend.domain.comment.dto.SimpleQuestionCommentResponse;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.repository.MemberRepository;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public DetailQuestionResponse get(Long id){

        Question question = questionRepository.getQuestionWithMemberWithAnswers(id).orElseThrow(QuestionNotFound::new);


        List<SimpleQuestionCommentResponse> questionCommentResponses = new ArrayList<>();

        for (QuestionComment questionComment : question.getQuestionComments()) {
            log.info("questionComment={}", questionComment.getContent());
            log.info("questionComment member id={}", questionComment.getMember().getId());
            log.info("questionComment member username={}", questionComment.getMember().getUsername());

            SimpleQuestionCommentResponse simpleQuestionCommentResponse = SimpleQuestionCommentResponse.builder()
                    .questionCommentId(questionComment.getId())
                    .memberId(questionComment.getMember().getId())
                    .userName(questionComment.getMember().getUsername())
                    .content(questionComment.getContent())
                    .createdAt(questionComment.getCreatedAt())
                    .modifiedAt(questionComment.getModifiedAt())
                    .build();

            questionCommentResponses.add(simpleQuestionCommentResponse);
        }


        List<ComplexAnswerResponse> complexAnswerResponses = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {

            List<SimpleAnswerCommentResponse> simpleAnswerCommentResponses = new ArrayList<>();
            for (AnswerComment answerComment : answer.getAnswerComments()) {
                SimpleAnswerCommentResponse simpleAnswerCommentResponse = SimpleAnswerCommentResponse.builder()
                        .answerCommentId(answerComment.getId())
                        .memberId(answerComment.getMember().getId())
                        .userName(answerComment.getMember().getUsername())
                        .content(answerComment.getContent())
                        .createAt(answerComment.getCreatedAt())
                        .modifiedAt(answerComment.getModifiedAt())
                        .build();
                simpleAnswerCommentResponses.add(simpleAnswerCommentResponse);
            }


            ComplexAnswerResponse complexAnswerResponse = ComplexAnswerResponse.builder()
                    //답변정보
                    .answerId(answer.getId())
                    .createdAt(answer.getCreatedAt())
                    .modifiedAt(answer.getModifiedAt())
                    .content(answer.getContent())
                    .votes(0L)
                    .isAccepted(answer.getIsAccepted())
                    //질문 댓글 정보
                    //작성자 정보
                    .answerMember(MemberResponse.toResponse(answer.getMember()))
                    //답변댓글 정보
                    .simpleAnswerCommentResponses(simpleAnswerCommentResponses)
                    .build();

            complexAnswerResponses.add(complexAnswerResponse);
        }


        DetailQuestionResponse detailQuestionResponse = DetailQuestionResponse.builder()
                .question(SimpleQuestionResponse.toResponse(question))
                .member(MemberResponse.toResponse(question.getMember()))
                .tags(question
                        .getQuestionTags()
                        .stream()
                        .map(questionTag -> questionTag.getTag().getName())
                        .collect(Collectors.toList()))
                //질문이랑 태그
                .answers(complexAnswerResponses)
                .questionComments(questionCommentResponses)
                .build();

    return detailQuestionResponse;
    }

    public MultiResponse<?> getList(PageRequest pageable, QuestionSearch questionSearch){

        PageImpl<QuestionResponse> questionResponses = new PageImpl<>(questionRepository.getList(pageable, questionSearch)
                .stream()
                .map(question -> QuestionResponse.builder()
                        .member(MemberResponse.toResponse(question.getMember()))
                        .question(SimpleQuestionResponse.toSummaryResponse(question))
                        .tags(question
                                .getQuestionTags()
                                .stream()
                                .map(questionTag -> questionTag.getTag().getName())
                                .collect(Collectors.toList()))
                        .build()
                )
                .collect(Collectors.toList()),pageable.of(), questionRepository.getCount());


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
        ).collect(Collectors.toList());
    }

    private void existsSameTitle(String title) {
        if (questionRepository.existsByTitle(title)) {
            throw new TitleDuplication();
        }
    }
}
