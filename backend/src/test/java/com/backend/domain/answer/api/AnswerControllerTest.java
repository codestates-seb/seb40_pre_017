package com.backend.domain.answer.api;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.repository.MemberRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AnswerController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;


    @MockBean
    private AnswerService answerService;

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
        AnswerCreate answerCreate = new AnswerCreate("답변 create 테스트 객체 생성입니다.");
        String content = gson.toJson(answerCreate);

        given(answerService.create(Mockito.anyLong(), Mockito.any(), Mockito.any(AnswerCreate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer", questionId)
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
        Long answerId = 1L;
        AnswerUpdate answerUpdate = new AnswerUpdate("답변 update 테스트 객체 생성입니다.");
        String content = gson.toJson(answerUpdate);

        given(answerService.update(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(AnswerUpdate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/question/{id}/answer/{answer-id}", questionId, answerId)
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
        Long answerId = 1L;

        given(answerService.delete(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/question/{id}/answer/{answer-id}", questionId, answerId)
                        .with(user(authMember))
                );

        //then
        actions.andExpect(status().isOk());

    }

    @Test
    void accept() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        given(answerService.accept(Mockito.anyLong(), Mockito.anyLong(),Mockito.anyLong()))
                .willReturn(1L);


        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/accept", questionId, answerId)
                        .with(user(authMember))
        );


        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void unAccept() throws Exception {
        //given
        Long questionId = 1L;
        Long answerId = 1L;

        given(answerService.unAccept(Mockito.anyLong(), Mockito.anyLong(),Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/question/{id}/answer/{answer-id}/accept/undo", questionId, answerId)
                        .with(user(authMember))
        );


        //then
        actions.andExpect(status().isOk());
    }

}