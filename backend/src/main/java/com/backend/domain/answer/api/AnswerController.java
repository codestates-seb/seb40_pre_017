package com.backend.domain.answer.api;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.member.service.AuthMember;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.SingleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/question/{id}")
@Validated
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/answer")
    public ResponseEntity<?> create(@CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @Valid @RequestBody AnswerCreate answerCreate) {

       Long result = answerService.create(id, authMember.getMemberId(), answerCreate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{answer-id}")
                .buildAndExpand(result)
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @PatchMapping("/answer/{answer-id}")
    public ResponseEntity<?> update( @CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId,
            @Valid @RequestBody AnswerUpdate answerUpdate) {

        Long result = answerService.update(answerId, authMember.getId(), answerUpdate);

        return ResponseEntity.ok(new SingleResponseDto<>(result));
    }

    @DeleteMapping("/answer/{answer-id}")
    public ResponseEntity<?> delete(@CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {

        Long result = answerService.delete(answerId, authMember.getMemberId());

        return ResponseEntity.ok(new SingleResponseDto(result));
    }

    @PostMapping("/answer/{answer-id}/accept")
    public ResponseEntity<?> accept(@CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {

        Long result = answerService.accept(id, answerId, authMember.getMemberId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/answer/{answer-id}/accept/undo")
    public ResponseEntity<?> unAccept(@CurrentMember AuthMember authMember,
            @PathVariable("id") @Positive Long id,
            @PathVariable("answer-id") @Positive Long answerId) {

        Long result = answerService.unAccept(id, answerId, authMember.getMemberId());

        return  ResponseEntity.ok(result);
    }



}
