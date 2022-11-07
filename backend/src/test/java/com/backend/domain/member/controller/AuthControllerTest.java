package com.backend.domain.member.controller;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.ReissueResponse;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.member.service.AuthService;
import com.backend.global.config.security.filter.JwtVerificationFilter;
import com.backend.global.jwt.JwtAuthenticationEntryPoint;
import com.backend.global.jwt.TokenProvider;
import com.backend.global.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.crypto.SecretKey;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        value = {
                "jwt.secret=c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK",
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

    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @SpyBean
    TokenProvider spyTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private Gson gson;

    private final TestUserDetailService testUserDetailService = new TestUserDetailService();

    private AuthMember authMember;


    @BeforeAll
    @Profile("dev")
    public void setup() {

        String password = passwordEncoder.encode("123456");
        Member member = Member.builder()
                .email(TestUserDetailService.UserName)
                .username("가나다")
                .password(password)
                .build();

        memberRepository.save(member);

        authMember = (AuthMember) testUserDetailService.loadUserByUsername(TestUserDetailService.UserName);
    }

    @AfterEach
    public void clear() {
        Mockito.reset(spyTokenProvider);
    }

    private String createToken(String email, List<String> roles, Date now, int expireMin, String secretKey) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put(KEY_ROLES, roles);
        byte[] keyBytes = Decoders.BASE64URL.decode("c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QasdfasdfasdfsadfsafsdafsfqtdHV0b3JpYWwK");
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
                "user@example.com",
                Collections.singletonList("ROLE_ADMIN"),
                new Date(),
                10,
                "accessSecretKey");
    }

    private String getRefreshToken() {
        return createToken(
                "user@example.com",
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


        willDoNothing().given(authService).signup(Mockito.any(SignUpRequest.class));

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
    public void givenRefreshToken_whenReissue_thenReturnAccessToken() throws Exception {

        Cookie cookie = new Cookie("name", "name");

        TokenDto tokenDto = TokenDto.builder()
                .grantType("bearer")
                .accessTokenExpiresIn(10L)
                .refreshToken(getRefreshToken())
                .accessToken(getAccessToken())
                .build();

        ReissueResponse reissueResponse = ReissueResponse.builder()
                .imageUrl("https://unsplash.com/photos/o-WP2j3o2lQ")
                .email("user@example.com")
                .username("가나다")
                .build();

        Claims claims = Jwts.claims();

        String refreshToken = getRefreshToken();

        doReturn(tokenDto).when(spyTokenProvider).generateTokenDto(Mockito.any(AuthMember.class));
        doReturn(claims).when(spyTokenProvider).parseClaims(Mockito.anyString());
        doReturn(new UsernamePasswordAuthenticationToken(authMember, authMember)).when(spyTokenProvider).getAuthentication(Mockito.anyString());
        given(authService.reissue(Mockito.anyString(), Mockito.any(HttpServletResponse.class)))
                .willReturn(reissueResponse);


        mockMvc.perform(
                        get("/users/reissue")
                                .header("Authorization", "Bearer " + refreshToken)
                                .cookie(cookie)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void logout() throws Exception {
        Cookie cookie = new Cookie("name","name");

        mockMvc.perform(
                delete("/users/logout")
                        .cookie(cookie)

        ).andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그인 성공")
    public void givenValidLoginDto_whenLogin_thenReturnAccessToken() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .email("user@example.com")
                .password("123456")
                .build();
        String content = gson.toJson(loginDto);

        mockMvc.perform(
                        post("/users/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패: 패스워드오류")
    public void givenNotMatchedPasswordDto_whenLogin_thenIsForbidden() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .email("user@example.com")
                .password("123") // 실제비밀번호 : 123456
                .build();

        String content = gson.toJson(loginDto);

        mockMvc.perform(
                post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isUnauthorized());


    }
}