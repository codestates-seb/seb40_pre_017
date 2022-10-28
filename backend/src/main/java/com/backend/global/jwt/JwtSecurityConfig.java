package com.backend.global.jwt;

import com.backend.global.config.security.filter.JwtVerificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /*
    TokenProvider 와 JwtVerificationFilter 를 SecurityConfig 에 적용하기 위한 클래스
     */
    private final TokenProvider tokenProvider;

    // TokenProvider -> JwtVerificationFilter 를 통해 Security 로직에 필터 적용
    @Override
    public void configure(HttpSecurity http) {
        JwtVerificationFilter customFilter = new JwtVerificationFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
