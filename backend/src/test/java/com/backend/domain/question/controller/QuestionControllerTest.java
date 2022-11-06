package com.backend.domain.question.controller;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.answer.dto.ComplexAnswerResponse;
import com.backend.domain.comment.dto.SimpleAnswerCommentResponse;
import com.backend.domain.comment.dto.SimpleQuestionCommentResponse;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.question.dto.response.DetailQuestionResponse;
import com.backend.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.domain.question.service.QuestionSearchService;
import com.backend.domain.question.service.QuestionService;
import com.backend.domain.tag.dto.TagDto;
import com.backend.domain.vote.application.VoteService;
import com.backend.domain.vote.dto.response.VoteStateResponse;
import com.backend.global.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    VoteService voteService;
    @MockBean
    QuestionService questionservice;

    @MockBean
    QuestionSearchService questionSearchService;

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

        authMember = (AuthMember) testUserDetailService.loadUserByUsername(TestUserDetailService.UserName, member);

    }

    @Test
    void create() throws Exception {

        TagDto java = new TagDto("java");
        TagDto python = new TagDto("python");

        List<TagDto> tags = new ArrayList<>();
        tags.add(java);
        tags.add(python);

        QuestionCreate questionCreate = QuestionCreate.builder()
                .title("질문생성을 위한 스텁 타이틀제목내용입니다.")
                .content("질문 생성 테스트를 위한 스텁 컨텐츠 내용입니다.")
                .tags(tags)
                .build();

        String content = gson.toJson(questionCreate);


        given(questionservice.create(Mockito.anyLong(), Mockito.any(QuestionCreate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk());

    }

    @Test
    void get() throws Exception {

        Long questionId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.get("/questions/{id}", questionId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk());
    }

    @Test
    void getList() throws Exception {

        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.get("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("filters", "NoAnswer")

        );
        actions.andExpect(status().isOk());
    }

    @Test
    void getSearchList() throws Exception {

        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.get("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("filters", "NoAcceptedAnswer")
                        .param("q", "java")

        );
        actions.andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        TagDto java = new TagDto("java");
        TagDto python = new TagDto("python");
        TagDto html = new TagDto("html");

        List<TagDto> tags = new ArrayList<>();
        tags.add(java);
        tags.add(python);
        tags.add(html);

        Long questionId = 1L;

        QuestionUpdate questionUpdate = QuestionUpdate.builder()
                .title("질문생성을 위한 스텁 타이틀제목내용입니다.")
                .content("질문 생성 테스트를 위한 스텁 컨텐츠 내용입니다.")
                .tags(tags)
                .build();

        String content = gson.toJson(questionUpdate);

        given(questionservice.update(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(QuestionUpdate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{id}", questionId)
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


        Long questionId = 1L;

        given(questionservice.delete(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/questions/{id}", questionId)
                        .with(user(authMember))
        );

        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void getVotes() throws Exception {
        Long questionId = 1L;
        List<VoteStateResponse.AnswerVoteState> answerVoteStates = new ArrayList<>();
        VoteStateResponse.AnswerVoteState answerVoteState = VoteStateResponse.AnswerVoteState.builder()
                .answerId(1L)
                .answerDownVote(false)
                .answerUpVote(true)
                .build();
        answerVoteStates.add(answerVoteState);

        VoteStateResponse res = VoteStateResponse.builder()
                .questionUpVote(false)
                .questionDownVote(true)
                .answerVoteStates(answerVoteStates)
                .build();

        given(voteService.getVotes(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(res);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/questions/{id}/votes", questionId)
                        .with(user(authMember))
        ).andExpect(status().isOk());
    }
}