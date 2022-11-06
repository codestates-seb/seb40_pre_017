package com.backend.domain.vote.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswerDownVote is a Querydsl query type for AnswerDownVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswerDownVote extends EntityPathBase<AnswerDownVote> {

    private static final long serialVersionUID = 317930867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswerDownVote answerDownVote = new QAnswerDownVote("answerDownVote");

    public final com.backend.domain.answer.domain.QAnswer answer;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.domain.member.domain.QMember member;

    public QAnswerDownVote(String variable) {
        this(AnswerDownVote.class, forVariable(variable), INITS);
    }

    public QAnswerDownVote(Path<? extends AnswerDownVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswerDownVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswerDownVote(PathMetadata metadata, PathInits inits) {
        this(AnswerDownVote.class, metadata, inits);
    }

    public QAnswerDownVote(Class<? extends AnswerDownVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new com.backend.domain.answer.domain.QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
    }

}

