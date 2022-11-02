package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.ReissueResponse;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.exception.EmailDuplication;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.exception.NotLoginMember;
import com.backend.domain.member.exception.UserNameDuplication;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.exception.TokenInvalid;
import com.backend.domain.refreshtoken.exception.TokenNotFound;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입
    @Transactional
    public Long signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailDuplication();
        }
        if (memberRepository.existsByUsername((signUpRequest.getUsername()))) {
            throw new UserNameDuplication();
        }
        // 비밀번호 암호화
        Member member = signUpRequest.encodePassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);
        // MemberId 반환
        return MemberResponseDto.of(savedMember).getId();
    }

    // 토큰 재발급
    @Transactional
    public ReissueResponse reissue(String refreshToken,
                                   HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNotFound::new);

        Claims claims = tokenProvider.parseClaims(refreshToken);


        Member member = memberRepository.findById(Long.parseLong(claims.getSubject()))
                .orElseThrow(MemberNotFound::new);

        AuthMember authMember = AuthMember.of(member);

        Long memberId = authMember.getMemberId();

        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);
        String newRTK = tokenDto.getRefreshToken();
        String newATK = tokenDto.getAccessToken();

        RefreshToken savedRefreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(NotLoginMember::new);

        if (!savedRefreshToken.getValue().equals(refreshToken)) {
            throw new TokenInvalid();
        }

        RefreshToken newRefreshToken = savedRefreshToken.updateValue(newRTK);
        refreshTokenRepository.save(newRefreshToken);

        Cookie refreshTokenToCookie = new Cookie("refreshToken", newRTK);
        refreshTokenToCookie.setMaxAge(60 * 60 * 24 * 14);
        refreshTokenToCookie.setHttpOnly(true);
        refreshTokenToCookie.setPath("/");

        response.addCookie(refreshTokenToCookie);

        response.setHeader("Authorization", "Bearer " + newATK);

        return ReissueResponse.toResponse(member);
    }

    // 로그아웃
    @Transactional
    public void logout(String refreshToken, HttpServletRequest request, HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNot:Found::new);
        // request 에서 refreshToken 쿠키를 찾아 삭제
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
        }
        refreshTokenRepository.deleteByValue(refreshToken);
    }

}
