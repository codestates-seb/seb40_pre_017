package com.backend.domain.vote.api;


import com.backend.domain.vote.application.QuestionVoteService;
import com.backend.domain.vote.dto.QuestionVoteCreate;
import com.backend.domain.vote.dto.QuestionVoteResponse;
import com.backend.domain.vote.dto.QuestionVoteUpdate;
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
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;

    @PostMapping("/comments")
    public ResponseEntity<QuestionVoteResponse>  create(
            @PathVariable("id") @Positive Long questionId,
            @Valid @RequestBody QuestionVoteCreate questionVoteCreate) {

        QuestionVoteResponse result = questionVoteService.create(questionVoteCreate, questionId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity<QuestionVoteResponse> update(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionVoteId,
            @Valid @RequestBody QuestionVoteUpdate questionVoteUpdate) {


        QuestionVoteResponse result = questionVoteService.update(questionVoteUpdate, questionVoteId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> delete(
            @PathVariable("id") @Positive Long questionId,
            @PathVariable("comment-id") @Positive Long questionVoteId) {

        Long id = questionVoteService.delete(questionVoteId);

        return ResponseEntity.ok(id);
    }




}
