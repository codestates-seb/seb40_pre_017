package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberPostDto;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.global.util.CustomAuthorityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    // 회원가입
    public MemberResponseDto createMember(MemberPostDto memberPostDto) {
        Member member = memberPostDto.toEntity();

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        Member savedMember = memberRepository.save(member);

        return savedMember.toResponseDto();
    }


}
