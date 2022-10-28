package com.backend.domain.comment.api;

import com.backend.domain.comment.application.QuestionCommentService;
import com.backend.domain.comment.dto.QuestionCommentCreate;
import com.backend.domain.comment.dto.QuestionCommentResponse;
import com.backend.domain.comment.dto.QuestionCommentUpdate;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<QuestionCommentResponse>  createComment(
            @PathVariable("id") @Positive Long questionId,
            @Valid @RequestBody QuestionCommentCreate questionCommentCreate) {

        QuestionCommentResponse result = questionCommentService.createComment(questionCommentCreate, questionId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<QuestionCommentResponse> updateComment(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionCommentId,
            @Valid @RequestBody QuestionCommentUpdate questionCommentUpdate) {


        QuestionCommentResponse result = questionCommentService.updateComment(questionCommentUpdate, questionCommentId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionCommentId) {

        Long id = questionCommentService.deleteComment(questionCommentId);

        return ResponseEntity.ok(id);
    }




}
