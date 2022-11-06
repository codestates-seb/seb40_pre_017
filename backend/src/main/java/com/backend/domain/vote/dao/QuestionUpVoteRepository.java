package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.QuestionUpVote;
import com.backend.domain.vote.dto.projection.QVoteCountProjection;
import com.backend.domain.vote.dto.projection.VoteCountProjection;
import com.backend.domain.vote.dto.response.VoteStateResponse;
import com.backend.domain.vote.exception.NoneStillVoted;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.backend.domain.answer.domain.QAnswer.*;
import static com.backend.domain.question.domain.QQuestion.question;
import static com.backend.domain.vote.domain.QAnswerDownVote.answerDownVote;
import static com.backend.domain.vote.domain.QAnswerUpVote.answerUpVote;
import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;
import static com.backend.domain.vote.domain.QQuestionUpVote.questionUpVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class QuestionUpVoteRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;


public Long undoUp(Long questionId, Long memberId) {

        return jpaQueryFactory.delete(questionUpVote)
                .where(questionUpVote.question.id.eq(questionId)
                        .and(questionUpVote.member.id.eq(memberId))
                ).execute();
    }

    public void up(QuestionUpVote questionUpVote) {

        em.persist(questionUpVote);
    }




}

