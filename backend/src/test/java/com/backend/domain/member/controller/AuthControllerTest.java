package com.backend.domain.member.controller;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.service.AuthService;
import com.backend.global.jwt.TokenProvider;
import com.backend.global.repository.MemberRepository;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        value = {
                "jwt.secret=accessSecretKey",
                "jwt.refresh-secret=refreshSecretKey",
                "jwt.expire-min=10",
                "jwt.refresh-expire-min=30"
        }
)
@AutoConfigureMockMvc
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    final int ONE_SECONDS = 1000;
    final int ONE_MINUTE = 60 * ONE_SECONDS;
    final String KEY_ROLES = "roles";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;
    @MockBean
    private AuthService authService;

    @SpyBean
    TokenProvider spyTokenProvider;

    @Autowired
    private Gson gson;


    @BeforeAll
    @Profile("dev")
    public void setup() {
        Member member = Member.builder()
                .email("abc@gmail.com")
                .username("가나다")
                .password("123456")
                .build();

        memberRepository.save(member);
    }

    @AfterEach
    public void clear() {
        Mockito.reset(spyTokenProvider);
    }

    private String createToken(String email, List<String> roles, Date now, int expireMin, String secretKey) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put(KEY_ROLES, roles);
        byte[] keyBytes = Decoders.BASE64URL.decode("c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK");
        SecretKey key= Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ONE_MINUTE * expireMin))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private String getAccessToken() {
        return createToken(
                "abc@gmail.com",
                Collections.singletonList("ROLE_ADMIN"),
                new Date(),
                10,
                "accessSecretKey");
    }

    private String getRefreshToken() {
        return createToken(
                "abc@gmail.com",
                Collections.singletonList("ROLE_ADMIN"),
                new Date(),
                30,
                "refreshSecretKey");
    }


    @Test
    void signup() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("abc@gmailcom", "123456", "잉스기", "", 0L);
        String content = gson.toJson(signUpRequest);



        given(authService.signup(Mockito.any(SignUpRequest.class)))
                .willReturn(1L);
        //when
        ResultActions actions = mockMvc.perform(
                post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isCreated());

    }

    @Test
    void reissue() {

    }

    @Test
    void logout() {

    }

    @Test
    public void givenNotExistsUserDto_whenLogin_thenIsForbidden() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "Not-Junhyunny")
                        .param("password", "123")
        ).andExpect(status().isForbidden());
    }
}