package com.backend.domain.comment.api;

import com.backend.domain.comment.application.AnswerCommentService;
import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.comment.dto.AnswerCommentCreate;
import com.backend.domain.comment.dto.AnswerCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("question/?/answer/{answer-id}")
@RequiredArgsConstructor
@Validated
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("/comments")
    public ResponseEntity<AnswerCommentResponse>  createComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerCommentCreate answerCommentCreate) {
        AnswerCommentResponse result = answerCommentService.createComment(answerCommentCreate, answerId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<AnswerCommentResponse> updateComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId,
            @Valid @RequestBody AnswerCommentUpdate answerCommentUpdate) {


        AnswerCommentResponse result = answerCommentService.updateComment(answerCommentUpdate, answerCommentId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId) {

        Long id = answerCommentService.deleteComment(answerCommentId);

        return ResponseEntity.ok(id);
    }


}
