package com.backend.domain.comment.domain;

import com.backend.domain.comment.dto.QuestionCommentUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionComment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionComment(String content,Question question, Member member) {
        this.content = content;
        this.question = question;
        this.member = member;
    }


    public static QuestionComment toEntity(String content, Question question, Member member) {
        QuestionComment questionComment = QuestionComment.builder()
                .content(content)
                .question(question)
                .member(member)
                .build();

        question.getQuestionComments().add(questionComment);

        return questionComment;
    }


    public void patch(QuestionCommentUpdate questionCommentUpdate) {
        if(questionCommentUpdate.getContent() != null)
            this.content = questionCommentUpdate.getContent();
    }
}
