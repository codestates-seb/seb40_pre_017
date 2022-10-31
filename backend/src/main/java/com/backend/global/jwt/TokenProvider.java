package com.backend.global.jwt;

import com.backend.domain.member.dto.TokenDto;
import com.backend.domain.member.service.AuthMember;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    /* 유저 정보로 JWT 토큰을 만들거나 토큰을 바탕으로 유저 정보를 가져옴
    *  JWT 토큰 관련 암호화, 복호화, 검증 로직
    */
    // TODO: 에러 전역 처리

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    @Value("${jwt.access-token-expiration-time}")
    private static long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${refresh-token-expiration-time}")
    private static long REFRESH_TOKEN_EXPIRE_TIME;

    private final Key key;


    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(AuthMember authMember) {
        // 권한들 가져오기
        String authorities = authMember.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authMember.getMemberId());
        claims.put("roles", authMember.getAuthorities());

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authMember.getEmail())                  // payload "sub": "name"
                .setClaims(claims)      // payload "auth": "ROLE_USER"
                .setExpiration(accessTokenExpiresIn)                   // payload "exp": 1516239022 (예시)
                .signWith(key, SignatureAlgorithm.HS512)          // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(authMember.getMemberId().toString())
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("roles") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        List<String> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
//        UserDetails principal = new User(claims.getSubject(), "", authorities);
        AuthMember auth = AuthMember.of(claims.get("id",Long.class), authorities);
        return new UsernamePasswordAuthenticationToken(auth, auth.getPassword(), auth.getAuthorities());
    }

    // 토큰 검증 - Jwts에서 던져주는 에러 활용
    public boolean validateToken(String token) {

        // validate jwt token
        try {
            parseClaims(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.");
            log.trace("JWT claims string is empty trace: {}", e);
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
