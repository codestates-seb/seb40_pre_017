package com.backend.domain.member.controller;

import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping()
    public ResponseEntity<Long> signup(@RequestBody SignUpRequest signUpRequest) {

        authService.signup(signUpRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    // 재발급
    @PostMapping("/reissue")
    public ResponseEntity<Long> reissue(@CookieValue("refreshToken") String refreshToken,
                                        HttpServletResponse response) {
        // 쿠키의 refreshToken 이 만료되어 없을 경우
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authService.reissue(refreshToken, response));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue("refreshToken") String refreshToken,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        authService.logout(refreshToken ,request, response);
        return ResponseEntity.ok().build();
    }

}


