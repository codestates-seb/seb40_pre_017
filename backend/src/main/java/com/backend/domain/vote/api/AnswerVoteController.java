package com.backend.domain.vote.api;


import com.backend.domain.member.service.AuthMember;
import com.backend.domain.vote.application.AnswerVoteService;
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
@RequestMapping("question/?/answer/{answer-id}")
@RequiredArgsConstructor
@Validated
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;

    @PostMapping("/upvote")
    public ResponseEntity<Long> up(
            @PathVariable("answer-id") @Positive Long answerId,
            @AuthenticationPrincipal AuthMember authMember) {

        answerVoteService.up(answerId, authMember.getMemberId());

        return ResponseEntity.ok(answerId);
    }

    @PostMapping("/upvote/undo")
    public ResponseEntity<Long> undoUp(
            @PathVariable("answer-id") @Positive Long answerId,
            @AuthenticationPrincipal AuthMember authMember) {

        answerVoteService.undoUp(answerId, authMember.getMemberId());

        return ResponseEntity.ok(answerId);
    }

    @PostMapping("/downvote")
    public ResponseEntity<Long> down(
            @PathVariable("answer-id") @Positive Long answerId,
            @AuthenticationPrincipal AuthMember authMember) {

        answerVoteService.down(answerId, authMember.getMemberId());

        return ResponseEntity.ok(answerId);
    }

    @PostMapping("/downvote/undo")
    public ResponseEntity<Long> undoDown(
            @PathVariable("answer-id") @Positive Long answerId,
            @AuthenticationPrincipal AuthMember authMember) {

        answerVoteService.undoDown(answerId,authMember.getMemberId());

        return ResponseEntity.ok(answerId);
    }

}
