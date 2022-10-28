package com.backend.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionCommentResponse {
    private Long questionCommentId;
}
