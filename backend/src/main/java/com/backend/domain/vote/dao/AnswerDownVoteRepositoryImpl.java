package com.backend.domain.vote.dao;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.backend.domain.vote.domain.QAnswerDownVote.answerDownVote;
import static com.backend.domain.vote.domain.QAnswerUpVote.answerUpVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class AnswerDownVoteRepositoryImpl implements AnswerDownVoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Long answerVoteUndoDown(Long answerId, Long memberId) {

        return jpaQueryFactory.delete(answerDownVote)
                .where(answerDownVote.answer.id.eq(answerId)
                        .and(answerDownVote.member.id.eq(memberId))
                ).execute();
    }


}

