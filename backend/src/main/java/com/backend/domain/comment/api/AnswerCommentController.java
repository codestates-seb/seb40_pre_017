package com.backend.domain.comment.api;

import com.backend.domain.comment.application.AnswerCommentService;
import com.backend.domain.comment.dto.AnswerCommentCreate;
import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("question/?/answer/{answer-id}")
@RequiredArgsConstructor
@Validated
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("/comments")
    public ResponseEntity<?>  create(
            @CurrentMember AuthMember authMember,
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerCommentCreate answerCommentCreate) {

        Long result = answerCommentService.create(authMember.getMemberId(), answerId, answerCommentCreate);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{comment-id}")
                .buildAndExpand(result)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<?> update(
            @CurrentMember AuthMember authMember,
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId,
            @Valid @RequestBody AnswerCommentUpdate answerCommentUpdate) {


        Long result = answerCommentService.update(authMember.getMemberId(), answerCommentId, answerCommentUpdate);

        return ResponseEntity.ok(new SingleResponseDto(result));
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<?> delete(
            @CurrentMember AuthMember authMember,
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId) {

        Long id = answerCommentService.delete(authMember.getMemberId(),answerCommentId);

        return ResponseEntity.ok(new SingleResponseDto(id));
    }


}
