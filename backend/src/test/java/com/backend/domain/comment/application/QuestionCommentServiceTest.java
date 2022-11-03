package com.backend.domain.comment.application;

import com.backend.domain.question.domain.Question;
import com.backend.domain.comment.dao.QuestionCommentRepository;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.comment.dto.QuestionCommentUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionCommentServiceTest {

    @Mock
    private QuestionCommentRepository questionCommentRepository;

    @InjectMocks
    private QuestionCommentService questionCommentService;

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


        String content = "답변 댓글 생성 로직 테스트를위한 컨텐츠";

        //when
        QuestionComment questionComment = QuestionComment.toEntity(content, question, member);


        //then
        assertEquals(content, questionComment.getContent());
        assertEquals(member.getEmail(), questionComment.getMember().getEmail());
        assertEquals(question.getTitle(), questionComment.getQuestion().getTitle());

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


        String content = "답변 댓글 생성 로직 테스트를위한 컨텐츠";

        QuestionComment questionComment = QuestionComment.toEntity(content, question, member);

        String NewContent = "This QuestionComment will be updated";
        QuestionCommentUpdate questioncommentUpdate = new QuestionCommentUpdate(NewContent);



        //when
        questionComment.patch(questioncommentUpdate);

        //then
        assertEquals(NewContent, questionComment.getContent());
    }


}