package com.backend.global.config.security.handler;

import com.backend.domain.member.service.AuthMember;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MemberAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RefreshTokenRepository refreshTokenRepository;

    public MemberAuthenticationSuccessHandler(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        AuthMember authMember = (AuthMember) authentication.getPrincipal();

        String refreshToken = response.getHeader("Set-Cookie").substring(13).split(";")[0];

        // RefreshToken 저장
        RefreshToken refresh = RefreshToken.builder()
                .key(authMember.getMemberId())
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refresh);
    }

}
