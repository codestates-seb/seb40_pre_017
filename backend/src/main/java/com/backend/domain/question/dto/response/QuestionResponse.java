package com.backend.domain.question.dto.response;

import com.backend.domain.member.dto.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class QuestionResponse {

    private List<String> tags;

    private MemberResponse member;

    private SimpleQuestionResponse question;


    @Builder
    public QuestionResponse(List<String> tags, MemberResponse member, SimpleQuestionResponse question) {
        this.tags = tags;
        this.member = member;
        this.question = question;
    }






}
