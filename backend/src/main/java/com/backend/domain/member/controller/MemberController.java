package com.backend.domain.member.controller;

import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.MemberUpdate;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.member.service.MemberService;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;



    @PatchMapping
    public ResponseEntity<?> update(
            @CurrentMember AuthMember authMember,
            @RequestBody @Valid MemberUpdate memberUpdate) {

        Long result = memberService.update(authMember.getMemberId(), memberUpdate);

        return ResponseEntity.ok(new SingleResponseDto(result));
    }


    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMyMemberInfo(@CurrentMember AuthMember authMember) {
        Long memberId = authMember.getMemberId();
        return ResponseEntity.ok(memberService.getMyInfo(memberId));
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponse> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }
}
