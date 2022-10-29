package com.backend.global.config.security.handler;

import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MemberAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public MemberAuthenticationSuccessHandler(RefreshTokenRepository refreshTokenRepository, TokenProvider tokenProvider) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 인증 정보를 기반으로 JWT 토큰 생성
        String refreshToken = response.getHeader("RefreshToken");

        // RefreshToken 저장
        RefreshToken refresh = RefreshToken.builder()
                .key(authentication.getName())
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refresh);


    }


}
