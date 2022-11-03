package com.backend.domain.tag.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TagQueryResponse {
    private Long QuestionId;
    private String tagName;



    @QueryProjection
    public TagQueryResponse(Long questionId, String tagName) {
        QuestionId = questionId;
        this.tagName = tagName;
    }
}
