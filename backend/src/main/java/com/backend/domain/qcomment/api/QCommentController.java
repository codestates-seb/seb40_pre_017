package com.backend.domain.qcomment.api;

import com.backend.domain.qcomment.application.QCommentService;
import com.backend.domain.qcomment.dto.QCommentCreate;
import com.backend.domain.qcomment.dto.QCommentResponse;
import com.backend.domain.qcomment.dto.QCommentUpdate;
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
public class QCommentController {

    private final QCommentService qCommentService;

    @PostMapping("/comments")
    public ResponseEntity<QCommentResponse>  createComment(
            @PathVariable("id") @Positive Long questionId,
            @Valid @RequestBody QCommentCreate qCommentCreate) {

        QCommentResponse result = qCommentService.createComment(qCommentCreate, questionId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<QCommentResponse> updateComment(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long qcommentId,
            @Valid @RequestBody QCommentUpdate qCommentUpdate) {


        QCommentResponse result = qCommentService.updateComment(qCommentUpdate, qcommentId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long qcommentId) {

        Long id = qCommentService.deleteComment(qcommentId);

        return ResponseEntity.ok(id);
    }


}
