package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberDto;
import com.backend.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member createMember(MemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .username(memberDto.getUsername())
                // 초기 평판: 0
                .reputation(0L)
                // TODO: 프로필 이미지 추가
                .profileImage("https://i.imgur.com/1ZQ3Z0u.png")
                .build();

        return memberRepository.save(member);
    }


}
