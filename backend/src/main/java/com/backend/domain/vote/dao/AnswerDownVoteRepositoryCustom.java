package com.backend.domain.vote.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDownVoteRepositoryCustom {


    Long answerVoteUndoDown(Long answerId, Long memberId);
}
