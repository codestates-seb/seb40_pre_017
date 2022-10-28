package com.backend.domain.question.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.domain.QuestionTag;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.dto.request.QuestionUpdate;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TagService tagService;

    /**
     * 1. dto에서 태그를 가져온다.
     * 2. 제목이 중복인지 확인한다.
     * 3. 태그를 등록한다 ( 있는 태그면 넘어가고 새 태그면 등록한다.)
     * 4.
     */
    @Transactional
    public Long create(QuestionCreate questionCreate) {

        existsSameTitle(questionCreate.getTitle());

        List<QuestionTag> questionTags = makeQuestionTags(questionCreate.getTags());

        // 현재 사용자 가져오는 로직으로 수정 필요

        Question question = Question.createQuestion(questionCreate, getMember(), questionTags);

        return questionRepository.save(question).getId();

    }

    public MultiResponse<?> getList(PageRequest pageable, QuestionSearch questionSearch){

        PageImpl<QuestionResponse> questionResponses = new PageImpl<>(questionRepository.getList(pageable, questionSearch)
                .stream()
                .map(question -> QuestionResponse.builder()
                        .member(MemberResponse.toResponse(question.getMember()))
                        .question(SimpleQuestionResponse.toResponse(question))
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
