package com.backend.domain.comment.domain;

import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.comment.dto.AnswerCommentResponse;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.global.Audit.Auditable;
import lombok.*;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerComment_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Builder
    public AnswerComment(String content, Answer answer, Member member) {
        this.content = content;
        this.answer = answer;
        this.member = member;
    }

    /**
     * param member 추가 필요
     */
    public static AnswerComment toEntity(String content,Answer answer,Member member ) {
        AnswerComment answerComment = AnswerComment.builder()
                .content(content)
                .answer(answer)
                .member(member)
                .build();

        answer.getAnswerComments().add(answerComment);

        return answerComment;
    }
    public AnswerCommentResponse toResponseDto() {
        return AnswerCommentResponse.builder()
                .answerCommentId(id)
                .build();
    }

    public void patch(AnswerCommentUpdate answerCommentUpdate) {
        if(answerCommentUpdate.getContent() != null)
            this.content = answerCommentUpdate.getContent();
    }
}
