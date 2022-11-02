package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.exception.EmailDuplication;
import com.backend.domain.member.exception.UserNameDuplication;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
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
        if(memberRepository.existsByUsername((signUpRequest.getUsername()))){
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
    public Long reissue(String refreshToken,
                        HttpServletResponse response) {

        Claims claims = tokenProvider.parseClaims(refreshToken);

        if (claims == null) {
            throw new RuntimeException("refreshToken 이 만료되었습니다.");
        }

        Member member = memberRepository.findById(Long.parseLong(claims.getSubject()))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        AuthMember authMember = AuthMember.of(member);

        Long memberId = authMember.getMemberId();

        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);
        String newRTK = tokenDto.getRefreshToken();
        String newATK = tokenDto.getRefreshToken();

        RefreshToken savedRefreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        if (!savedRefreshToken.getValue().equals(refreshToken)) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        RefreshToken newRefreshToken = savedRefreshToken.updateValue(newRTK);
        refreshTokenRepository.save(newRefreshToken);

        Cookie refreshTokenToCookie = new Cookie("refreshToken", newRTK);
        refreshTokenToCookie.setMaxAge(60 * 60 * 24 * 14);
        refreshTokenToCookie.setHttpOnly(true);
        refreshTokenToCookie.setPath("/");

        response.addCookie(refreshTokenToCookie);

        response.setHeader("Authorization", "Bearer " + newATK);

        return authMember.getMemberId();
    }

    // 로그아웃
    @Transactional
    public void logout(String token, HttpServletRequest request, HttpServletResponse response) {
        // request 에서 refreshToken 쿠키를 찾아 삭제
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        refreshTokenRepository.deleteByValue(token);
    }

}
