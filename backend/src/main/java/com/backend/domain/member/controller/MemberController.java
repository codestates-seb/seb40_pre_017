package com.backend.domain.member.controller;

import com.backend.domain.member.domain.Member;
import com.backend.domain.member.dto.MemberDto;
import com.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        // TODO: 에러메세지 추가
        if (ObjectUtils.isEmpty(memberDto.getEmail()) || ObjectUtils.isEmpty(memberDto.getPassword()) || ObjectUtils.isEmpty(memberDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member result = this.memberService.createMember(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDto.builder()
                .id(result.getId())
                .build());
    }
}
