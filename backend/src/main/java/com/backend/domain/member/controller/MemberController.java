package com.backend.domain.member.controller;

import com.backend.domain.member.dto.MemberResponse;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.dto.MemberUpdate;
import com.backend.domain.member.dto.SignUpRequest;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.member.service.MemberService;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<MemberResponseDto> create(@RequestBody SignUpRequest signUpRequest) {
        MemberResponseDto member = memberService.create(signUpRequest);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("users")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PatchMapping
    public ResponseEntity<?> update(
            @CurrentMember AuthMember authMember,
            @RequestBody @Valid MemberUpdate memberUpdate) {

        Long result = memberService.update(authMember.getMemberId(), memberUpdate);

        return ResponseEntity.ok(new SingleResponseDto(result));
    }


    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponse> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }
}
