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
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(1L)
                .build();

        given(memberService.getMyInfo(Mockito.anyLong()))
                .willReturn(memberResponseDto);

        //when
        ResultActions actions = mockMvc.perform(
                get("/user/me")
                        .with(user(authMember))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk());
    }

    @Test
    void getMemberInfo() {
    }
}