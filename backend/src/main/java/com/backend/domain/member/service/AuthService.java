package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.dto.TokenPostDto;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입
    @Transactional
    public MemberResponseDto signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            // TODO: 전역 예외 처리
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = signUpRequest.encodePassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.of(savedMember);
    }


    // 토큰 재발급
    @Transactional
    public TokenDto reissue(String accessToken, String refreshToken) {
        // TODO: 전역 에러 처리
        // Refresh Token 검증
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // Access Token 에서 Member ID 가져오기
        AuthMember authMember = (AuthMember) tokenProvider.getAuthentication(accessToken).getPrincipal();
        Long memberId = authMember.getId();


        // 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken savedRefreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // Refresh Token 일치하는지 검사
        if (!savedRefreshToken.getValue().equals(refreshToken)) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);

        // DB 정보 업데이트
        RefreshToken newRefreshToken = savedRefreshToken.updateValue(refreshToken);
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    // 로그아웃
    @Transactional
    public void logout(String token) {
        refreshTokenRepository.deleteByValue(token);
    }

}
