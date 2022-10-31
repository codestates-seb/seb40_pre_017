package com.backend.global.config.security.filter;

import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.jwt.TokenProvider;
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
        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);

        String refreshToken = tokenDto.getRefreshToken();

        Cookie refreshTokenToCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenToCookie.setMaxAge(60 * 60 * 24 * 14);
        refreshTokenToCookie.setHttpOnly(true);

        response.addCookie(refreshTokenToCookie);

        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
//        response.setHeader("RefreshToken", refreshToken);

        // response body에 member의 emial, username, ImageUrl을 담아서 보내준다.
        response.getWriter().write(
                "{" +
                        "\"email\":\"" + authMember.getEmail() + "\","
                        + "\"username\":\"" + authMember.getMemberUsername() + "\","
                        + "\"imageUrl\":\"" + authMember.getProfileImage() + "\"" +
                        "}"
        );


        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }


}
