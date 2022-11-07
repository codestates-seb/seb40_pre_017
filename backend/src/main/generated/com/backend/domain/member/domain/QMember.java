package com.backend.domain.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1712969869L;

    public static final QMember member = new QMember("member1");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    public final ListPath<com.backend.domain.answer.domain.Answer, com.backend.domain.answer.domain.QAnswer> answers = this.<com.backend.domain.answer.domain.Answer, com.backend.domain.answer.domain.QAnswer>createList("answers", com.backend.domain.answer.domain.Answer.class, com.backend.domain.answer.domain.QAnswer.class, PathInits.DIRECT2);

    public final EnumPath<Authority> authority = createEnum("authority", Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<com.backend.domain.question.domain.Question, com.backend.domain.question.domain.QQuestion> questions = this.<com.backend.domain.question.domain.Question, com.backend.domain.question.domain.QQuestion>createList("questions", com.backend.domain.question.domain.Question.class, com.backend.domain.question.domain.QQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Long> reputation = createNumber("reputation", Long.class);

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

