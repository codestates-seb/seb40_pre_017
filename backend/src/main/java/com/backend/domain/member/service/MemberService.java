package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberPostDto;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public MemberResponseDto createMember(MemberPostDto memberPostDto) {
        Member member = memberPostDto.toEntity();

        Member savedMember = memberRepository.save(member);

        return savedMember.toResponseDto();
    }


}
