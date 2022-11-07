package com.backend.global.jwt;

import com.backend.global.config.security.filter.JwtVerificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;

    // TokenProvider -> JwtVerificationFilter 를 통해 Security 로직에 필터 적용
    @Override
    public void configure(HttpSecurity http) {
        JwtVerificationFilter customFilter = new JwtVerificationFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
