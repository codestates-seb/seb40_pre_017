package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthMember extends Member implements UserDetails {

    private Long memberId;
    private String email;
    private String password;
    private List<String> roles;
    private String nickname;

    private AuthMember(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.roles = List.of(member.getAuthority().toString());
        this.nickname = member.getUsername();
    }

    private AuthMember(Long id, List<String> roles) {
        this.memberId = id;
        this.password = "";
        this.roles = roles;
    }

    public static AuthMember of(Member member) {
        return new AuthMember(member);
    }

    public static AuthMember of(Long id, List<String> roles) {
        return new AuthMember(id, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.get(0)));
    }

    public String getMemberUsername() {
        return nickname;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
