package com.backend.global.config.security;

import com.backend.domain.refreshtoken.repository.RefreshTokenRepository;
import com.backend.global.config.security.filter.JwtAuthenticationFilter;
import com.backend.global.config.security.filter.JwtVerificationFilter;
import com.backend.global.config.security.handler.MemberAuthenticationSuccessHandler;
import com.backend.global.jwt.JwtAccessDeniedHandler;
import com.backend.global.jwt.JwtAuthenticationEntryPoint;
import com.backend.global.jwt.TokenProvider;
import com.backend.global.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity // (debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    Spring Security 설정
     */
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final MemberRepository memberRepository;

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

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
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
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .apply(new CustomFilterConfigurer())
                .and()
                // 권한 관리 대상 지정
                .authorizeRequests()
                .antMatchers("/**").permitAll() // 모든 요청에 대해 허용
                .anyRequest().permitAll() // 나머지 요청 허용
                .and()
        ;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000"); // 로컬
        configuration.addAllowedOrigin("http://ec2-13-209-84-129.ap-northeast-2.compute.amazonaws.com:8080/");
        configuration.addAllowedOrigin("https://api.taekgil.xyz/");
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.addAllowedOrigin("http://practice-fe-cms98.s3-website.ap-northeast-2.amazonaws.com/"); // 프론트 IPv4 주소
        configuration.addAllowedMethod("*"); // 모든 메소드 허용.
        configuration.addAllowedHeader("*");
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenProvider, authenticationManager, memberRepository);
            jwtAuthenticationFilter.setFilterProcessesUrl("/users/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler(refreshTokenRepository));
//            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(tokenProvider);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

}
