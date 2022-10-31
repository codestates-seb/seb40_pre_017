package com.backend.domain.answer.api;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.dto.AnswerPatchDto;
import com.backend.domain.answer.dto.AnswerPostDto;
import com.backend.domain.answer.dto.AnswerResponseDto;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/question")
@Validated
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @PostMapping("/{id}/answer")
    public ResponseEntity<?> postAnswer(@CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @Valid @RequestBody AnswerPostDto answerPostDto) {

//        Long answerId = answerService.createAnswer(id, authMember.getMemberId(), answerPostDto);
        Long answerId = answerService.createAnswer(id, 1L, answerPostDto);

        return new ResponseEntity<>(new SingleResponseDto(answerId), HttpStatus.CREATED);

    }

    @PatchMapping("/{id}/answer/{answer-id}")
    public ResponseEntity<AnswerResponseDto> patchAnswer(
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerPatchDto answerPatchDto) {

        answerPatchDto.setAnswerId(answerId);
        AnswerResponseDto result = answerService.updateAnswer(answerPatchDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/answer/{answer-id}")
    public ResponseEntity<?> deleteAnswer(
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/answer/{answer-id}/accept")
    public ResponseEntity<?> acceptAnswer(
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {
        answerService.acceptAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/answer/{answer-id}/accept/undo")
    public ResponseEntity<?> unAcceptAnswer(
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {
        answerService.unAcceptAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
