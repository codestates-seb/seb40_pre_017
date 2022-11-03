package com.backend.domain.comment.dao;

import com.backend.domain.question.domain.Question;
import com.backend.domain.comment.domain.QuestionComment;
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
class QuestionCommentRepositoryTest {

    @Autowired
    private QuestionCommentRepository questionCommentRepository;


    @MockBean
    private QuestionRepositoryImpl questionRepository;

    @MockBean
    private JPAQueryFactory jpaQueryFactory;


    @Test
    void saveQuestionComment() {

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


        QuestionComment questionComment = QuestionComment.toEntity(content, question, member);


        //when
        QuestionComment savedQuestionComment = questionCommentRepository.save(questionComment);

        //then
        assertNotNull(savedQuestionComment);
        assertEquals(question.getTitle(), savedQuestionComment.getQuestion().getTitle());
        assertEquals(questionComment.getContent(), savedQuestionComment.getContent());
        assertEquals(member.getEmail(), savedQuestionComment.getMember().getEmail());
    }

    @Test
    void deleteQuestionComment() {
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


        QuestionComment questionComment = QuestionComment.toEntity(content, question, member);

        //when
        QuestionComment savedQuestionComment = questionCommentRepository.save(questionComment);
        questionCommentRepository.delete(questionComment);

        //then
        Optional<QuestionComment> optionalQuestionComment = questionCommentRepository.findById(1L);
        assertTrue(optionalQuestionComment.isEmpty());

    }


    @Test
    void findVerifiedQuestionComment() {
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


        QuestionComment questionComment = QuestionComment.toEntity(content, question, member);
        //when

        QuestionComment savedQuestionComment = questionCommentRepository.save(questionComment);
        Optional<QuestionComment> findQuestionComment = questionCommentRepository.findById(savedQuestionComment.getId());

        assertTrue(findQuestionComment.isPresent());
        assertTrue(findQuestionComment.get().getContent().equals(savedQuestionComment.getContent()));
    }
}