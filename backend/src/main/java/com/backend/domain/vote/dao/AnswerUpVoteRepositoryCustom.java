package com.backend.domain.vote.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AnswerUpVoteRepositoryCustom {

    Long answerVoteUndoUp(Long answerId, Long memberId);

}
