package com.backend.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    /*
    SecurityContext 에서 전역으로 유저 정보를 제공하기 위한 클래스
     */

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            log.error("SecurityContext 에 인증 정보가 없습니다.");
            throw new AccessDeniedException("SecurityContext 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}
