package com.backend.domain.answer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 514835131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    public final ListPath<com.backend.domain.comment.domain.AnswerComment, com.backend.domain.comment.domain.QAnswerComment> answerComments = this.<com.backend.domain.comment.domain.AnswerComment, com.backend.domain.comment.domain.QAnswerComment>createList("answerComments", com.backend.domain.comment.domain.AnswerComment.class, com.backend.domain.comment.domain.QAnswerComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<com.backend.domain.vote.domain.AnswerDownVote, com.backend.domain.vote.domain.QAnswerDownVote> downVotes = this.<com.backend.domain.vote.domain.AnswerDownVote, com.backend.domain.vote.domain.QAnswerDownVote>createList("downVotes", com.backend.domain.vote.domain.AnswerDownVote.class, com.backend.domain.vote.domain.QAnswerDownVote.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAccepted = createBoolean("isAccepted");

    public final com.backend.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.backend.domain.question.domain.QQuestion question;

    public final ListPath<com.backend.domain.vote.domain.AnswerUpVote, com.backend.domain.vote.domain.QAnswerUpVote> upVotes = this.<com.backend.domain.vote.domain.AnswerUpVote, com.backend.domain.vote.domain.QAnswerUpVote>createList("upVotes", com.backend.domain.vote.domain.AnswerUpVote.class, com.backend.domain.vote.domain.QAnswerUpVote.class, PathInits.DIRECT2);

    public final NumberPath<Integer> voteCount = createNumber("voteCount", Integer.class);

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new com.backend.domain.question.domain.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

