package com.backend.global.config.security.filter;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.jwt.TokenProvider;
import com.backend.global.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        SignUpRequest signUpRequest = om.readValue(request.getInputStream(), SignUpRequest.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        AuthMember authMember = (AuthMember) authResult.getPrincipal();

         Member member = memberRepository.findById(authMember.getMemberId())
                 .orElseThrow(MemberNotFound::new);


        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);

        String refreshToken = tokenDto.getRefreshToken();

        Cookie refreshTokenToCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenToCookie.setMaxAge(60 * 60 * 24 * 14);
//        refreshTokenToCookie.setHttpOnly(true);
        refreshTokenToCookie.setPath("/");

        response.addCookie(refreshTokenToCookie);

        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());

        // response body에 member의 emial, username, ImageUrl을 담아서 보내준다.
        response.getWriter().write(
                "{" +
                        "\"email\":\"" + member.getEmail() + "\","
                        + "\"username\":\"" + member.getUsername() + "\","
                        + "\"imageUrl\":\"" + member.getProfileImage() + "\"" +
                        "}"
        );


        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

}
