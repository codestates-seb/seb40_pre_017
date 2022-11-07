package com.backend.domain.comment.application;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.comment.dao.AnswerCommentRepository;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AnswerCommentServiceTest {

    @Mock
    private AnswerCommentRepository answerCommentRepository;

    @InjectMocks
    private AnswerCommentService answerCommentService;

    @Test
    void create() {
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

        Answer answer = Answer.builder()
                .content("답변 생성 비즈니스 로직 테스트용 답변생성")
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();

        String content = "답변 댓글 생성 로직 테스트를위한 컨텐츠";

        //when
        AnswerComment answerComment = AnswerComment.toEntity(content, answer, member);


        //then
        assertEquals(content, answerComment.getContent());
        assertEquals(member.getEmail(), answerComment.getMember().getEmail());
        assertEquals(answer.getContent(), answerComment.getAnswer().getContent());

    }

    @Test
    void update() {
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

        Answer answer = Answer.builder()
                .content("답변 생성 비즈니스 로직 테스트용 답변생성")
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();

        String content = "답변 댓글 생성 로직 테스트를위한 컨텐츠";
        AnswerComment answerComment = AnswerComment.toEntity(content, answer, member);

        String NewContent = "This AnswerComment will be updated";
        AnswerCommentUpdate answercommentUpdate = new AnswerCommentUpdate(NewContent);



        //when
        answerComment.patch(answercommentUpdate);

        //then
        assertEquals(NewContent, answerComment.getContent());
    }


}