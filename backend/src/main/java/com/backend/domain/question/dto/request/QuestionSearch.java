package com.backend.domain.question.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionSearch {
    private static final int MAX_SIZE = 2000;
    @Builder.Default
    private Integer page =1;
    @Builder.Default
    private Integer size = 20;

    @Override
    public String toString() {
        return "QuestionSearch{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }

    public long getOffset() {
        return (long) (Math.min(1,page) - 1) *Math.min(size,MAX_SIZE);
    }
}
