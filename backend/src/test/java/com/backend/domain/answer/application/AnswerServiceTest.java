package com.backend.domain.answer.application;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.global.repository.MemberRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerServiceTest {

    @Mock
    private MemberRepository memberRepository;


    @Autowired
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private AnswerService answerService;


    @Autowired
    private Gson gson;


    @Test
    void create() throws Exception {

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

        AnswerCreate answerCreate = new AnswerCreate(content);

        Answer answer = answerCreate.toEntity(question, member);

        assertEquals(question.getTitle(), answer.getQuestion().getTitle());
        assertEquals(answer.getContent(), answer.getContent());
        assertEquals(member.getEmail(), answer.getMember().getEmail());

    }

    @Test
    void update() throws Exception {



    }

    @Test
    void delete() throws Exception {

    }

    @Test
    void accept() throws Exception {

    }

    @Test
    void unAccept() throws Exception {


    }
}