package com.backend.domain.vote.dto.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class VoteCountProjection {

    private Long questionId;
    private Long answerId;
    private Long questionUpVoteCount;
    private Long questionDownVoteCount;
    private Long answerUpVoteCount;
    private Long answerDownVoteCount;


    @QueryProjection
    public VoteCountProjection(Long questionId, Long answerId, Long questionUpVoteCount, Long questionDownVoteCount, Long answerUpVoteCount, Long answerDownVoteCount) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.questionUpVoteCount = questionUpVoteCount;
        this.questionDownVoteCount = questionDownVoteCount;
        this.answerUpVoteCount = answerUpVoteCount;
        this.answerDownVoteCount = answerDownVoteCount;
    }
}
