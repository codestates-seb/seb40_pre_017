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
    public ResponseEntity<AnswerCommentResponse>  create(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerCommentCreate answerCommentCreate) {
        AnswerCommentResponse result = answerCommentService.create(answerCommentCreate, answerId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<AnswerCommentResponse> update(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId,
            @Valid @RequestBody AnswerCommentUpdate answerCommentUpdate) {


        AnswerCommentResponse result = answerCommentService.update(answerCommentUpdate, answerCommentId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> delete(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long answerCommentId) {

        Long id = answerCommentService.delete(answerCommentId);

        return ResponseEntity.ok(id);
    }


}
