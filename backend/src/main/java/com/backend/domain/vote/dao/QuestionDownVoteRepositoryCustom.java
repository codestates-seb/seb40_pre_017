package com.backend.domain.vote.dao;

import com.backend.domain.vote.dto.response.VoteStateResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDownVoteRepositoryCustom {



    Long questionVoteUndoDown(Long questionId, Long memberId);

}
