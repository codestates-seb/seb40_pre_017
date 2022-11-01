package com.backend.domain.comment.api;

import com.backend.domain.comment.application.QuestionCommentService;
import com.backend.domain.comment.dto.QuestionCommentCreate;
import com.backend.domain.comment.dto.QuestionCommentUpdate;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("question/{id}")
@RequiredArgsConstructor
@Validated
public class QuestionCommentController {

    private final QuestionCommentService questionCommentService;

    @PostMapping("/comments")

    public ResponseEntity<?>  create(
            @CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long questionId,
            @Valid @RequestBody QuestionCommentCreate questionCommentCreate) {

        Long result = questionCommentService.create(authMember.getMemberId(), questionCommentCreate, questionId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SingleResponseDto(result));
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<?> update(
            @CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionCommentId,
            @Valid @RequestBody QuestionCommentUpdate questionCommentUpdate) {


        Long result = questionCommentService.update(authMember.getMemberId(), questionCommentUpdate, questionCommentId);

        return ResponseEntity.ok(new SingleResponseDto(result));
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<?> delete(
            @CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionCommentId) {

        Long result = questionCommentService.delete(authMember.getMemberId(),questionCommentId);

        return ResponseEntity.ok(new SingleResponseDto(result));
    }

}
