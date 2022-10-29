package com.backend.domain.question.controller;

import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.question.dto.response.QuestionResponse;
import com.backend.domain.question.service.QuestionService;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.request.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<Long> create(@Valid @RequestBody QuestionCreate questionCreate) {
        return ResponseEntity.ok(questionService.create(questionCreate));
    }

    @GetMapping("/questions/{id}")
    public void get(@PathVariable Long id) {
        questionService.get(id);
    }

    @GetMapping("/questions")
    public ResponseEntity<MultiResponse<?>> getList(PageRequest pageable, @ModelAttribute QuestionSearch questionSearch) {
        log.info("page= {}",pageable.getPage());
        log.info("offset = {}",pageable.getOffset());
        log.info("size = {}",pageable.getSize());


        return ResponseEntity.ok(questionService.getList(pageable,questionSearch));
    }

    @PatchMapping("questions/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @Valid @RequestBody QuestionUpdate questionUpdate){

        return ResponseEntity.ok(questionService.update(id, questionUpdate));
    }

    @DeleteMapping("questions/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){


        return ResponseEntity.ok(questionService.delete(id));


    }

}
