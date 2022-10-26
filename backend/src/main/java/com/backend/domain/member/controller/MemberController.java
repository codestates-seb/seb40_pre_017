package com.backend.domain.member.controller;

import com.backend.domain.member.dto.MemberPostDto;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberPostDto memberPostDto) {

        MemberResponseDto member = memberService.createMember(memberPostDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
