package com.backend.global.config.security;

import com.backend.global.jwt.JwtAccessDeniedHandler;
import com.backend.global.jwt.JwtAuthenticationEntryPoint;
import com.backend.global.jwt.JwtSecurityConfig;
import com.backend.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    Spring Security 설정
     */

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // h2 db console 접근을 위한 설정
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // csrf 비활성화
                .csrf().disable()

                // 예외처리 핸들러
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()

                // h2 console 사용을 위한 설정
                .headers().frameOptions().sameOrigin()
                .and()

                // 세션 사용 안함 (stateless)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 권한 관리 대상 지정
                .authorizeRequests()
                .antMatchers("/**").permitAll() // 모든 요청에 대해 허용
                .anyRequest().permitAll() // 나머지 요청 허용
                .and()

                // jwt 필터 추가
                .apply(new JwtSecurityConfig(tokenProvider));

    }

}
