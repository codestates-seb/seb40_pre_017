package com.backend.domain.vote.dto.projection;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.domain.vote.dto.projection.QVoteCountProjection is a Querydsl Projection type for VoteCountProjection
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QVoteCountProjection extends ConstructorExpression<VoteCountProjection> {

    private static final long serialVersionUID = 354028581L;

    public QVoteCountProjection(com.querydsl.core.types.Expression<Long> questionId, com.querydsl.core.types.Expression<Long> answerId, com.querydsl.core.types.Expression<Long> questionUpVoteCount, com.querydsl.core.types.Expression<Long> questionDownVoteCount, com.querydsl.core.types.Expression<Long> answerUpVoteCount, com.querydsl.core.types.Expression<Long> answerDownVoteCount) {
        super(VoteCountProjection.class, new Class<?>[]{long.class, long.class, long.class, long.class, long.class, long.class}, questionId, answerId, questionUpVoteCount, questionDownVoteCount, answerUpVoteCount, answerDownVoteCount);
    }

}

