package com.backend.domain.question.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class QuestionSearchQuery {
    private String q;


    public QuestionSearchQuery(String q) {
        this.q = q;
    }



    public QuestionSearch queryParsing(String q){

        Pattern p = Pattern.compile("\\[([ㄱ-ㅎ가-핳a-zA-Z\\d]+)\\]");

        // 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(q);			// 문자열 설정

        List<String> tagNames = new ArrayList<>();

        while (m.find()) {
            tagNames.add(m.group().substring(1,m.group().length()-1));
        }
        // asdf dfdf
        // tagnames = [java]
        String noTagOneSpaceString = m.replaceAll("").replaceAll("\\s+", " ");

        return QuestionSearch.of(noTagOneSpaceString,tagNames);


    }
}
