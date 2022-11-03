package com.backend.domain.member.service;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.MemberUpdate;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.exception.UserNameDuplication;
import com.backend.global.repository.MemberRepository;
import com.backend.global.util.SecurityUtil;
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

    // 회원가입
    public MemberResponseDto create(SignUpRequest signUpRequest) {
        Member member = signUpRequest.signup();

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.encodePassword(encryptedPassword);

        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.of(savedMember);
    }

    public Long update(Long memberId, MemberUpdate memberUpdate) {

        if(memberRepository.existsByUsername((memberUpdate.getUsername()))){
            throw new UserNameDuplication();
        }

        String encryptedPassword = passwordEncoder.encode(memberUpdate.getPassword());

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);

        member.patch(memberUpdate, encryptedPassword);

        return memberId;
    }

    //
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


}
