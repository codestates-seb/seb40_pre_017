package com.backend.domain.vote.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class VoteStateResponse {

    private Boolean questionUpVote ;
    private Boolean questionDownVote ;
    private List<AnswerVoteState> answerVoteStates;


    @Builder
    public VoteStateResponse(Boolean questionUpVote, Boolean questionDownVote, List<AnswerVoteState> answerVoteStates) {
        this.questionUpVote = questionUpVote;
        this.questionDownVote = questionDownVote;
        this.answerVoteStates = answerVoteStates;
    }



    @Getter
    public static class AnswerVoteState {

        private Long answerId;
        private Boolean answerUpVote;
        private Boolean answerDownVote;

        @Builder
        public AnswerVoteState(Long answerId, Boolean answerUpVote, Boolean answerDownVote) {
            this.answerId = answerId;
            this.answerUpVote = answerUpVote;
            this.answerDownVote = answerDownVote;
        }


    }


}
