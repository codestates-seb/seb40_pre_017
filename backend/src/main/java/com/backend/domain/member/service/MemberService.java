package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.MemberUpdate;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.exception.UserNameDuplication;
import com.backend.global.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    public Long update(Long memberId, MemberUpdate memberUpdate) {

        if (memberRepository.existsByUsername((memberUpdate.getUsername()))) {
            throw new UserNameDuplication();
        }

        String encryptedPassword = passwordEncoder.encode(memberUpdate.getPassword());

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);

        member.patch(memberUpdate, encryptedPassword);

        return memberId;
    }

    @Transactional(readOnly = true)
    public MemberResponse getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFound::new);
        return MemberResponse.toResponse(member);
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기


    public MemberResponse getMyInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
        return MemberResponse.toResponse(member);

    }

}
