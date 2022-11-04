package com.backend.domain.member.controller;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.MemberUpdate;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.member.service.AuthService;
import com.backend.domain.member.service.MemberService;
import com.backend.global.repository.MemberRepository;

import static org.mockito.ArgumentMatchers.doubleThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


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
                .reputation(0L)
                .profileImage("https://unsplash.com/photos/o-WP2j3o2lQ")
                .build();

        memberRepository.save(member);

        authMember = (AuthMember) testUserDetailService.loadUserByUsername(TestUserDetailService.UserName);

    }

    @Test
    void update() throws Exception {
        //given
        MemberUpdate memberUpdate = new MemberUpdate("123456", "캘로그", "https://unsplash.com/photos/nLcr3Bfd52Y");

        String content = gson.toJson(memberUpdate);

        given(memberService.update(Mockito.any(), Mockito.any(MemberUpdate.class)))
                .willReturn(1L);
        //when
        ResultActions actions = mockMvc.perform(
                patch("/user")
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void getMyMemberInfo() throws Exception {
        //given
        MemberResponse memberResponse = MemberResponse.builder()
                .profileImage("https://unsplash.com/photos/o-WP2j3o2lQ")
                .reputation(0L)
                .userId(1L)
                .username("가나다")
                .link("https://unsplash.com/photos/X8aCMv55Dw4")
                .build();

        given(memberService.getMyInfo(Mockito.anyLong()))
                .willReturn(memberResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/user/me")
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getMemberInfo() throws Exception {
        //given
        String email = "user@example.com";

        MemberResponse memberResponse = MemberResponse.builder()
                .profileImage("https://unsplash.com/photos/o-WP2j3o2lQ")
                .reputation(0L)
                .userId(1L)
                .username("가나다")
                .link("https://unsplash.com/photos/X8aCMv55Dw4")
                .build();

        given(memberService.getMemberInfo(Mockito.anyString()))
                .willReturn(memberResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/user/{email}",email)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(memberResponse.getUserId()))
                .andExpect(jsonPath("$.username").value(memberResponse.getUsername()))
                .andExpect(jsonPath("$.profileImage").value(memberResponse.getProfileImage()))
                .andExpect(jsonPath("$.reputation").value(memberResponse.getReputation()))
                .andExpect(jsonPath("$.link").value(memberResponse.getLink()));


    }
}