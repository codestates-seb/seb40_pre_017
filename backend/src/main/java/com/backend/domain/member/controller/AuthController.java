package com.backend.domain.member.controller;

import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.dto.TokenPostDto;
import com.backend.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping()
    public ResponseEntity<Long> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signUpRequest));
    }

    // 재발급
    @PostMapping("/reissue")
    public ResponseEntity<Long> reissue(@CookieValue("refreshToken") String refreshToken,
                                        HttpServletResponse response) throws Exception {

        // 쿠키의 refreshToken 이 만료되어 없을 경우
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(authService.reissue(refreshToken, response));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue("refreshToken") String refreshToken,
                                       HttpServletRequest request, HttpServletResponse response) {
        authService.logout(refreshToken);

        // request 에서 refreshToken 쿠키를 찾아 삭제
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return ResponseEntity.ok().build();
    }

}


