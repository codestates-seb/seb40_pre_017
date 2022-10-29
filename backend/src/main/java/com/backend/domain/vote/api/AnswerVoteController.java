package com.backend.domain.vote.api;


import com.backend.domain.vote.application.AnswerVoteService;
import com.backend.domain.vote.dto.AnswerVoteCreate;
import com.backend.domain.vote.dto.AnswerVoteResponse;
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
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;

    @PostMapping("/upvote")
    public ResponseEntity<Long>  up(
            @PathVariable("answer-id") @Positive Long answerId) {
        answerVoteService.up(answerId);


        return ResponseEntity.ok(answerId);
    }

    @PostMapping("/upvote/undo")
    public ResponseEntity<AnswerVoteResponse>  undoUp(
            @PathVariable("answer-id") @Positive Long answerId) {
         answerVoteService.undoUP(answerId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/downvote")
    public ResponseEntity<AnswerVoteResponse>  down(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerVoteCreate answerVoteCreate) {

        answerVoteService.down(answerId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/downvote/undo")
    public ResponseEntity<AnswerVoteResponse>  undoDown(
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerVoteCreate answerVoteCreate) {

            answerVoteService.undoDown(answerId);

        return ResponseEntity.ok(result);
    }

}
