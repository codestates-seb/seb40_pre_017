package com.backend.domain.vote.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionDownVote is a Querydsl query type for QuestionDownVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionDownVote extends EntityPathBase<QuestionDownVote> {

    private static final long serialVersionUID = 62454619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionDownVote questionDownVote = new QQuestionDownVote("questionDownVote");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.backend.domain.question.domain.QQuestion question;

    public QQuestionDownVote(String variable) {
        this(QuestionDownVote.class, forVariable(variable), INITS);
    }

    public QQuestionDownVote(Path<? extends QuestionDownVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionDownVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionDownVote(PathMetadata metadata, PathInits inits) {
        this(QuestionDownVote.class, metadata, inits);
    }

    public QQuestionDownVote(Class<? extends QuestionDownVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new com.backend.domain.question.domain.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

