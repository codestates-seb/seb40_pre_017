package com.backend.domain.comment.api;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.comment.application.QuestionCommentService;
import com.backend.domain.comment.dto.QuestionCommentCreate;
import com.backend.domain.comment.dto.QuestionCommentUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;


    @MockBean
    private QuestionCommentService questionCommentService;

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
    void create() throws Exception {
        //given

        Long questionId = 1L;
        QuestionCommentCreate questionCommentCreate = new QuestionCommentCreate("질문 create 테스트 객체 생성입니다.");
        String content = gson.toJson(questionCommentCreate);

        given(questionCommentService.create(Mockito.anyLong(), Mockito.any(QuestionCommentCreate.class),Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/comments", questionId)
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isCreated());

    }

    @Test
    void update() throws Exception {
        //given

        Long questionId = 1L;
        Long commentId =1L;
        QuestionCommentUpdate questionCommentUpdate = new QuestionCommentUpdate("질문 update 테스트 객체 생성입니다.");
        String content = gson.toJson(questionCommentUpdate);

        given(questionCommentService.update(Mockito.anyLong(), Mockito.any(QuestionCommentUpdate.class),Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/question/{id}/comments/{comment-id}", questionId,commentId)
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        //given
        Long questionId = 1L;
        Long commentId = 1L;

        given(questionCommentService.delete(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/question/{id}/comments/{comment-id}", questionId,commentId)
                        .with(user(authMember))
        );

        //then
        actions.andExpect(status().isOk());

    }
}