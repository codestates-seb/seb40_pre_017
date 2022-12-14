package com.backend.domain.vote.api;


import com.backend.domain.member.service.AuthMember;
import com.backend.domain.vote.application.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("question/{id}")
@RequiredArgsConstructor
@Validated
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;

    @PostMapping("/upvote")
    public ResponseEntity<Long> up(
            @PathVariable("id") @Positive Long questionId,
            @AuthenticationPrincipal AuthMember authMember) {

        questionVoteService.up(questionId, authMember.getMemberId());

        return ResponseEntity.ok(questionId);
    }

    @PostMapping("/upvote/undo")
    public ResponseEntity<Long> undoUp(
            @PathVariable("id") @Positive Long questionId,
            @AuthenticationPrincipal AuthMember authMember) {

        questionVoteService.undoUp(questionId, authMember.getMemberId());

        return ResponseEntity.ok(questionId);
    }

    @PostMapping("/downvote")
    public ResponseEntity<Long> down(
            @PathVariable("id") @Positive Long questionId,
            @AuthenticationPrincipal AuthMember authMember) {

        questionVoteService.down(questionId, authMember.getMemberId());

        return ResponseEntity.ok(questionId);
    }

    @PostMapping("/downvote/undo")
    public ResponseEntity<Long> undoDown(
            @PathVariable("id") @Positive Long questionId,
            @AuthenticationPrincipal AuthMember authMember) {

        questionVoteService.undoDown(questionId, authMember.getMemberId());

        return ResponseEntity.ok(questionId);
    }
    
}
