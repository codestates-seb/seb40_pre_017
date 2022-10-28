package com.backend.domain.member.controller;

import com.backend.domain.member.dto.MemberPostDto;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.dto.TokenPostDto;
import com.backend.domain.member.service.AuthService;
import com.backend.global.config.security.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping()
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberPostDto memberRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(memberRequestDto));
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberPostDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }
    // 재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenPostDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
