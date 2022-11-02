package com.backend.domain.answer.dao;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.question.repository.QuestionRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;


    @MockBean
    private QuestionRepositoryImpl questionRepository;

    @MockBean
    private JPAQueryFactory jpaQueryFactory;

    @Test
    void saveAnswerTest() {

        //given
        Question question = Question.builder()
                .title("가나다")
                .view(0L)
                .content("답변 생성 비즈니스로직 테스트용 질문입니다.")
                .build();
        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();

        String content = "답변 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();

        //when
        Answer savedAnswer = answerRepository.save(answer);

        //then
        assertNotNull(savedAnswer);
        assertEquals(question.getTitle(), savedAnswer.getQuestion().getTitle());
        assertEquals(answer.getContent(), savedAnswer.getContent());
        assertEquals(member.getEmail(), savedAnswer.getMember().getEmail());

    }
}