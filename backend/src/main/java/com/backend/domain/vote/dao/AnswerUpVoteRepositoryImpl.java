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
public class AnswerUpVoteRepositoryImpl implements AnswerUpVoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Long answerVoteUndoUp(Long answerId, Long memberId) {

        return jpaQueryFactory.delete(answerUpVote)
                .where(answerUpVote.answer.id.eq(answerId)
                        .and(answerUpVote.member.id.eq(memberId))
                ).execute();
    }



}

