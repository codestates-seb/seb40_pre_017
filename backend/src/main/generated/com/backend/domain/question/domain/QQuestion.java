package com.backend.domain.question.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -995151093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    public final ListPath<com.backend.domain.answer.domain.Answer, com.backend.domain.answer.domain.QAnswer> answers = this.<com.backend.domain.answer.domain.Answer, com.backend.domain.answer.domain.QAnswer>createList("answers", com.backend.domain.answer.domain.Answer.class, com.backend.domain.answer.domain.QAnswer.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<com.backend.domain.vote.domain.QuestionDownVote, com.backend.domain.vote.domain.QQuestionDownVote> downVotes = this.<com.backend.domain.vote.domain.QuestionDownVote, com.backend.domain.vote.domain.QQuestionDownVote>createList("downVotes", com.backend.domain.vote.domain.QuestionDownVote.class, com.backend.domain.vote.domain.QQuestionDownVote.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAnswered = createBoolean("isAnswered");

    public final com.backend.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<com.backend.domain.comment.domain.QuestionComment, com.backend.domain.comment.domain.QQuestionComment> questionComments = this.<com.backend.domain.comment.domain.QuestionComment, com.backend.domain.comment.domain.QQuestionComment>createList("questionComments", com.backend.domain.comment.domain.QuestionComment.class, com.backend.domain.comment.domain.QQuestionComment.class, PathInits.DIRECT2);

    public final ListPath<QuestionTag, QQuestionTag> questionTags = this.<QuestionTag, QQuestionTag>createList("questionTags", QuestionTag.class, QQuestionTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final ListPath<com.backend.domain.vote.domain.QuestionUpVote, com.backend.domain.vote.domain.QQuestionUpVote> upVotes = this.<com.backend.domain.vote.domain.QuestionUpVote, com.backend.domain.vote.domain.QQuestionUpVote>createList("upVotes", com.backend.domain.vote.domain.QuestionUpVote.class, com.backend.domain.vote.domain.QQuestionUpVote.class, PathInits.DIRECT2);

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public final NumberPath<Integer> voteCount = createNumber("voteCount", Integer.class);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.domain.member.domain.QMember(forProperty("member")) : null;
    }

}

