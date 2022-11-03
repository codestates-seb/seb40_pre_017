package com.backend.domain.question.controller;

import com.backend.domain.member.service.AuthMember;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionSearch;
import com.backend.domain.question.dto.request.QuestionSearchQuery;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.question.dto.response.DetailQuestionResponse;
import com.backend.domain.question.service.QuestionSearchService;
import com.backend.domain.question.service.QuestionService;
import com.backend.global.Annotation.CurrentMember;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.request.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionSearchService questionSearchService;

    @PostMapping("/questions")
    public ResponseEntity<Long> create(@CurrentMember AuthMember authMember, @Valid @RequestBody QuestionCreate questionCreate) {

     Long memberId = authMember.getMemberId();

        return ResponseEntity.ok(questionService.create(memberId,questionCreate));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<DetailQuestionResponse> get(@PathVariable Long id) {

        return ResponseEntity.ok(questionService.get(id));
    }

    @GetMapping("/questions")
    public ResponseEntity<MultiResponse<?>> getList(@ModelAttribute PageRequest pageable) {
        log.info("page= {}",pageable.getPage());
        log.info("offset = {}",pageable.getOffset());
        log.info("size = {}",pageable.getSize());
        log.info("filters = {}",pageable.getFilterEnums());


        return ResponseEntity.ok(questionService.getList(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<MultiResponse<?>> getSearchList(PageRequest pageable, @ModelAttribute QuestionSearchQuery questionSearchQuery) {

        QuestionSearch questionSearch = questionSearchQuery.queryParsing(questionSearchQuery.getQ());

        return ResponseEntity.ok(questionSearchService.getList(pageable,questionSearch));
    }


    @PatchMapping("questions/{id}")
    public ResponseEntity<Long> update(@CurrentMember AuthMember authMember, @PathVariable Long id, @Valid @RequestBody QuestionUpdate questionUpdate){

        Long memberId = authMember.getMemberId();

        return ResponseEntity.ok(questionService.update(memberId, id, questionUpdate));
    }

    @DeleteMapping("questions/{id}")
    public ResponseEntity<Long> delete(@CurrentMember AuthMember authMember, @PathVariable Long id){

        Long memberId = authMember.getMemberId();

        return ResponseEntity.ok(questionService.delete(memberId, id));


    }

}
