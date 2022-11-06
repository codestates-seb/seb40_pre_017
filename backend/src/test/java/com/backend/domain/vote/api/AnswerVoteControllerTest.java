package com.backend.domain.vote.api;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.vote.application.AnswerVoteService;
import com.backend.global.repository.MemberRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerVoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;


    @MockBean
    private AnswerVoteService answerVoteService;

    @Autowired
    private Gson gson;

    private final TestUserDetailService testUserDetailService = new TestUserDetailService();

    private AuthMember authMember;

    @BeforeAll
    @Profile("dev")
    public void setup() {
        Member member = Member.builder()
                .email(TestUserDetailService.UserName)
                .username("가나다")
                .password("123456")
                .build();

        memberRepository.save(member);

        authMember = (AuthMember) testUserDetailService.loadUserByUsername(TestUserDetailService.UserName);

    }



    @Test
    void up() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        willDoNothing().given(answerVoteService).up(Mockito.anyLong(), Mockito.any());

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/upvote", questionId,answerId)
                        .with(user(authMember))


        );
        //then
        actions.andExpect(status().isOk());

    }

    @Test
    void undoUp() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        willDoNothing().given(answerVoteService).undoUp(Mockito.anyLong(), Mockito.any());

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/upvote/undo", questionId,answerId)
                        .with(user(authMember))


        );
        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void down() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        willDoNothing().given(answerVoteService).down(Mockito.anyLong(), Mockito.any());

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/downvote", questionId,answerId)
                        .with(user(authMember))

        );
        //then
        actions.andExpect(status().isOk());

    }

    @Test
    void undoDown() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        willDoNothing().given(answerVoteService).undoDown(Mockito.anyLong(), Mockito.any());

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/downvote/undo", questionId, answerId)
                        .with(user(authMember))


        );
        //then
        actions.andExpect(status().isOk());

    }
}