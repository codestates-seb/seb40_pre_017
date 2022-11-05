package com.backend.domain.vote.dao;

import com.backend.domain.vote.dto.response.VoteStateResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionUpVoteRepositoryCustom {

    VoteStateResponse getVotes(Long memberId, Long questionId);

    Long questionVoteUndoUp(Long questionId, Long memberId);


}
