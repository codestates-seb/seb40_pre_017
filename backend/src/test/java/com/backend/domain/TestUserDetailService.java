package com.backend.domain;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Profile("test")
@NoArgsConstructor
public class TestUserDetailService implements UserDetailsService {

    public static final String UserName ="user@example.com";


    private Member getMember() {

        return Member.builder()
                .email(UserName)
                .username("가나다")
                .password("123456")
                .build();

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(UserName)) {
            return AuthMember.of(getMember());
        }
        return null;
    }
}
