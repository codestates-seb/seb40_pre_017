package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.backend.domain.vote.domain.QAnswerDownVote.answerDownVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class AnswerDownVoteRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;



    public Long undoDown(Long answerId, Long memberId) {

        return jpaQueryFactory.delete(answerDownVote)
                .where(answerDownVote.answer.id.eq(answerId)
                        .and(answerDownVote.member.id.eq(memberId))
                ).execute();

    }

    public void down(AnswerDownVote answerDownVote) {

        em.persist(answerDownVote);
    }




}

