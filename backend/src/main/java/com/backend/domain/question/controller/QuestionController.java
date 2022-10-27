package com.backend.domain.question.controller;

import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<Long> createQuestion(@Valid @RequestBody QuestionCreate questionCreate) {
        return ResponseEntity.ok(questionService.createQuestion(questionCreate));
    }

    @PatchMapping("questions/{id}")
    public ResponseEntity<Long> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionUpdate questionUpdate){

        return ResponseEntity.ok(questionService.updateQuestion(id, questionUpdate));
    }

    @DeleteMapping("questions/{id}")
    public void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion

    }

}
