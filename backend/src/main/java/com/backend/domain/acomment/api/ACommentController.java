package com.backend.domain.acomment.api;

import com.backend.domain.acomment.application.ACommentService;
import com.backend.domain.acomment.dto.ACommentPatchDto;
import com.backend.domain.acomment.dto.ACommentPostDto;
import com.backend.domain.acomment.dto.ACommentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("question/?/answer/{answer-id}")
@Validated
public class ACommentController {

    private ACommentService aCommentService;

    public ACommentController(ACommentService aCommentService) {
        this.aCommentService = aCommentService;
    }

    @PostMapping("/comments")
    public ResponseEntity postAComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody ACommentPostDto aCommentPostDto) {
        aCommentPostDto.setAnswerId(answerId);

        ACommentResponseDto result = aCommentService.createAComment(aCommentPostDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity patchAComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long acommentId,
            @Valid @RequestBody ACommentPatchDto aCommentPatchDto) {

        aCommentPatchDto.setAcommentId(acommentId);
        ACommentResponseDto result = aCommentService.updateAComment(aCommentPatchDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity deleteComment(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("comment-id") @Positive Long acommentId) {

        aCommentService.deleteAComment(acommentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
