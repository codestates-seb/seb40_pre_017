package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.ReissueResponse;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.exception.EmailDuplication;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.exception.NotLoginMember;
import com.backend.domain.member.exception.UserNameDuplication;
import com.backend.domain.refreshtoken.domain.RefreshToken;
import com.backend.domain.refreshtoken.exception.TokenInvalid;
import com.backend.domain.refreshtoken.exception.TokenNotFound;
import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.jwt.TokenProvider;
import com.backend.global.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailDuplication();
        }
        if (memberRepository.existsByUsername((signUpRequest.getUsername()))) {
            throw new UserNameDuplication();
        }
        // 비밀번호 암호화
        Member member = signUpRequest.encodePassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);
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

        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRTK)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());


        response.setHeader("Authorization", "Bearer " + newATK);

        return ReissueResponse.toResponse(member);
    }

    // 로그아웃
    @Transactional
    public void logout(String refreshToken, HttpServletRequest request, HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNotFound::new);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(0)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        refreshTokenRepository.deleteByKey(Long.valueOf(tokenProvider.parseClaims(refreshToken).getSubject()));
    }

}
