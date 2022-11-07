package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.QuestionDownVote;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class QuestionDownVoteRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;



    public Long undoDown(Long questionId, Long memberId) {

        return jpaQueryFactory.delete(questionDownVote)
                .where(questionDownVote.question.id.eq(questionId)
                        .and(questionDownVote.member.id.eq(memberId))
                ).execute();
    }

    public void down(QuestionDownVote questionDownVote) {

        em.persist(questionDownVote);
    }




}

