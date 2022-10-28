package com.backend.domain.question.service;

import com.backend.domain.question.domain.Question;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.tag.dto.TagDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("질문을 등록한다.")
    void createQuestion() {
        TagDto tag1 = new TagDto("java");
        TagDto tag2 = new TagDto("python");

        List<TagDto> tags = List.of(tag1, tag2);
        String title = "adfasdfsadfasdfasdfasdfㅁㄴㅇㄹㄴㅇㄹ";
        String content = "ㅁㄴㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㄴㅁㅇㄹㅁㄴㅇㄹ";
        QuestionCreate dto = QuestionCreate.builder()
                .title(title)
                .content(content)
                .tags(tags)
                .build();

        Long questionId = questionService.create(dto);
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);
        Assertions.assertThat(question.getTitle()).isEqualTo(title);
        Assertions.assertThat(question.getContent()).isEqualTo(content);
        Assertions.assertThat(question.getQuestionTags().size()).isEqualTo(2);

    }



}