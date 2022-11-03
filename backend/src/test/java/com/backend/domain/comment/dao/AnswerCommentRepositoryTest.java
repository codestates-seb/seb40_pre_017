package com.backend.domain.comment.dao;

import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.repository.QuestionRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerCommentRepositoryTest {

    @Autowired
    private AnswerCommentRepository answerCommentRepository;


    @MockBean
    private QuestionRepositoryImpl questionRepository;

    @MockBean
    private JPAQueryFactory jpaQueryFactory;


    @Test
    void saveAnswerComment() {

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

        String content = "답변 댓글 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();

        AnswerComment answerComment = AnswerComment.toEntity(content, answer, member);


        //when
        AnswerComment savedAnswerComment = answerCommentRepository.save(answerComment);

        //then
        assertNotNull(savedAnswerComment);
        assertEquals(answer.getContent(), savedAnswerComment.getAnswer().getContent());
        assertEquals(answerComment.getContent(), savedAnswerComment.getContent());
        assertEquals(member.getEmail(), savedAnswerComment.getMember().getEmail());
    }

    @Test
    void deleteAnswerComment() {
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

        AnswerComment answerComment = AnswerComment.toEntity(content, answer, member);

        //when
        AnswerComment savedAnswerComment = answerCommentRepository.save(answerComment);
        answerCommentRepository.delete(answerComment);

        //then
        Optional<AnswerComment> optionalAnswerComment = answerCommentRepository.findById(1L);
        assertTrue(optionalAnswerComment.isEmpty());

    }


    @Test
    void findVerifiedAnswerComment() {
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
        AnswerComment answerComment = AnswerComment.toEntity(content, answer, member);
        //when

        AnswerComment savedAnswerComment = answerCommentRepository.save(answerComment);
        Optional<AnswerComment> findAnswerComment = answerCommentRepository.findById(savedAnswerComment.getId());

        assertTrue(findAnswerComment.isPresent());
        assertTrue(findAnswerComment.get().getContent().equals(savedAnswerComment.getContent()));
    }
}