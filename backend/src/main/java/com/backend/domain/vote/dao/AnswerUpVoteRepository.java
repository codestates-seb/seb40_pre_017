package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.AnswerUpVote;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.backend.domain.vote.domain.QAnswerUpVote.answerUpVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class AnswerUpVoteRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public Long undoUp(Long answerId, Long memberId) {

        return jpaQueryFactory.delete(answerUpVote)
                .where(answerUpVote.answer.id.eq(answerId)
                        .and(answerUpVote.member.id.eq(memberId))
                ).execute();
    }

    public void up(AnswerUpVote answerUpVote) {

        em.persist(answerUpVote);
    }




}

