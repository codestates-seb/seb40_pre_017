package com.backend.domain.vote.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswerUpVote is a Querydsl query type for AnswerUpVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswerUpVote extends EntityPathBase<AnswerUpVote> {

    private static final long serialVersionUID = -107444052L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswerUpVote answerUpVote = new QAnswerUpVote("answerUpVote");

    public final com.backend.domain.answer.domain.QAnswer answer;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.domain.member.domain.QMember member;

    public QAnswerUpVote(String variable) {
        this(AnswerUpVote.class, forVariable(variable), INITS);
    }

    public QAnswerUpVote(Path<? extends AnswerUpVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswerUpVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswerUpVote(PathMetadata metadata, PathInits inits) {
        this(AnswerUpVote.class, metadata, inits);
    }

    public QAnswerUpVote(Class<? extends AnswerUpVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new com.backend.domain.answer.domain.QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
    }

}

