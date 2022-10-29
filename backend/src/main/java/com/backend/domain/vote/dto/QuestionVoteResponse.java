package com.backend.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionVoteResponse {
    private Long questionVoteId;
}
