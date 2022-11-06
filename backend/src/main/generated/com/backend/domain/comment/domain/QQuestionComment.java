package com.backend.domain.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionComment is a Querydsl query type for QuestionComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionComment extends EntityPathBase<QuestionComment> {

    private static final long serialVersionUID = -765975647L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionComment questionComment = new QQuestionComment("questionComment");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.backend.domain.question.domain.QQuestion question;

    public QQuestionComment(String variable) {
        this(QuestionComment.class, forVariable(variable), INITS);
    }

    public QQuestionComment(Path<? extends QuestionComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionComment(PathMetadata metadata, PathInits inits) {
        this(QuestionComment.class, metadata, inits);
    }

    public QQuestionComment(Class<? extends QuestionComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new com.backend.domain.question.domain.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

