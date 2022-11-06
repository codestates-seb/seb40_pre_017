package com.backend.domain.vote.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionUpVote is a Querydsl query type for QuestionUpVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionUpVote extends EntityPathBase<QuestionUpVote> {

    private static final long serialVersionUID = 352624788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionUpVote questionUpVote = new QQuestionUpVote("questionUpVote");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.backend.domain.question.domain.QQuestion question;

    public QQuestionUpVote(String variable) {
        this(QuestionUpVote.class, forVariable(variable), INITS);
    }

    public QQuestionUpVote(Path<? extends QuestionUpVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionUpVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionUpVote(PathMetadata metadata, PathInits inits) {
        this(QuestionUpVote.class, metadata, inits);
    }

    public QQuestionUpVote(Class<? extends QuestionUpVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new com.backend.domain.question.domain.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

