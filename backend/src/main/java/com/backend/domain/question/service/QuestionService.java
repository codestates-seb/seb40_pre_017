package com.backend.domain.question.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.domain.QuestionTag;
import com.backend.domain.question.dto.QuestionCreateDto;
import com.backend.domain.question.exception.TitleDuplicate;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.tag.domain.Tag;
import com.backend.domain.tag.dto.TagDto;
import com.backend.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
    public Long createQuestion(QuestionCreateDto questionCreateDto){

        existsSameTitle(questionCreateDto.getTitle());

        List<QuestionTag> questionTags = new ArrayList<>();

        questionCreateDto.getTags().forEach(tagDto -> {
            Tag tag = tagService.addTag(tagDto);
            QuestionTag questionTag = QuestionTag.createQuestionTag(tag);
            questionTags.add(questionTag);
        }
        );

//        for (TagDto tagDto : questionCreateDto.getTags()){
//            Tag tag = tagService.addTag(tagDto);
//            QuestionTag questionTag = QuestionTag.createQuestionTag(tag);
//            questionTags.add(questionTag);
//        }

        // 현재 사용자 가져오는 로직으로 수정 필요

        Question question = Question.createQuestion(questionCreateDto, getMember(), questionTags);
        return questionRepository.save(question).getId();

    }

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

    private void existsSameTitle(String title) {
        if(questionRepository.existsByTitle(title)){
            throw new TitleDuplicate();
        }
    }
}
