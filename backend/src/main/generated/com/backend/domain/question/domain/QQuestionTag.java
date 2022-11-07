package com.backend.domain.question.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionTag is a Querydsl query type for QuestionTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionTag extends EntityPathBase<QuestionTag> {

    private static final long serialVersionUID = 1613116559L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionTag questionTag = new QQuestionTag("questionTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuestion question;

    public final com.backend.domain.tag.domain.QTag tag;

    public QQuestionTag(String variable) {
        this(QuestionTag.class, forVariable(variable), INITS);
    }

    public QQuestionTag(Path<? extends QuestionTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionTag(PathMetadata metadata, PathInits inits) {
        this(QuestionTag.class, metadata, inits);
    }

    public QQuestionTag(Class<? extends QuestionTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
        this.tag = inits.isInitialized("tag") ? new com.backend.domain.tag.domain.QTag(forProperty("tag")) : null;
    }

}

