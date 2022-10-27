package com.backend.domain.acomment.api;

import com.backend.domain.acomment.application.ACommentService;
import com.backend.domain.acomment.dto.ACommentUpdate;
import com.backend.domain.acomment.dto.ACommentCreate;
import com.backend.domain.acomment.dto.ACommentResponse;
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
public class ACommentController {

    private final ACommentService aCommentService;

    @PostMapping("/comments")
    public ResponseEntity<ACommentResponse>  createComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody ACommentCreate aCommentCreate) {
        ACommentResponse result = aCommentService.createComment(aCommentCreate, answerId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<ACommentResponse> updateComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long acommentId,
            @Valid @RequestBody ACommentUpdate aCommentUpdate) {


        ACommentResponse result = aCommentService.updateComment(aCommentUpdate, acommentId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> deleteComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long acommentId) {

        Long id = aCommentService.deleteComment(acommentId);

        return ResponseEntity.ok(id);
    }


}
