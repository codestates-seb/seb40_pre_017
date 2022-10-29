package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.LoginDto;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.dto.TokenPostDto;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public MemberResponseDto signup(LoginDto loginDto) {
        if (memberRepository.existsByEmail(loginDto.getEmail())) {
            // TODO: 전역 예외 처리
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = loginDto.toEntity(passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.of(savedMember);
    }

    // 로그인
    @Transactional

    public String login(LoginDto loginDto) {

        // Login ID/PW 를 기반으로 AuthenticationToken 생성


        // 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        // loadUserByUsername 메서드의 리턴값을 기반으로 Authentication 객체를 만들고, 그 안에 UserDetails 객체를 넣어줌
        // 그리고 나서 UserDetails 객체의 getPassword() 메서드를 통해 DB에 저장되어 있는 비밀번호를 가져옴
        // 그리고 입력받은 비밀번호와 UserDetails 객체의 getPassword() 메서드를 통해 가져온 비밀번호를 비교함
        // 비밀번호가 일치하면 Authentication 객체를 리턴하고, 일치하지 않으면 예외를 던짐

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        // 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto((AuthMember) authentication.getPrincipal());

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        System.out.println("refreshToken = " + refreshToken);
        // 토큰 발급
        return tokenDto.getRefreshToken();
    }

    // 토큰 재발급
    @Transactional
    public TokenDto reissue(TokenPostDto tokenRequestDto) {
        // TODO: 전역 에러 처리
        // Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto((AuthMember) authentication.getPrincipal());

        // DB 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

}
