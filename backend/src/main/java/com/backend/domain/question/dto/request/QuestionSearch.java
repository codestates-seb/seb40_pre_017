package com.backend.domain.question.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class  QuestionSearch {

    private String query;
    private List<String> tagNames;

    private QuestionSearch(String query, List<String> tagNames) {
        this.query = query;
        this.tagNames = tagNames;
    }

    static public QuestionSearch of(String query, List<String> tagNames){
        return new QuestionSearch(query, tagNames);
    }




}
