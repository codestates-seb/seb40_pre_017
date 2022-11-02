package com.backend.domain.member.controller;

import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.ReissueResponse;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.service.AuthService;
import com.backend.domain.refreshtoken.exception.TokenNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponse> reissue(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                                   HttpServletResponse response) {

        return ResponseEntity.ok(authService.reissue(refreshToken, response));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken",required = false) String refreshToken,
                                       HttpServletRequest request,
                                       HttpServletResponse response)  {
        authService.logout(refreshToken ,request, response);
        return ResponseEntity.ok().build();
    }

}


