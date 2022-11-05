package com.backend.domain.vote.dao;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class QuestionDownVoteRepositoryImpl implements QuestionDownVoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Long questionVoteUndoDown(Long questionId, Long memberId) {

        return jpaQueryFactory.delete(questionDownVote)
                .where(questionDownVote.question.id.eq(questionId)
                        .and(questionDownVote.member.id.eq(memberId))
                ).execute();
    }



}

