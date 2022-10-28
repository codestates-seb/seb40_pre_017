package com.backend.domain.qcomment.domain;

import com.backend.domain.member.domain.Member;
import com.backend.domain.qcomment.dto.QCommentResponse;
import com.backend.domain.qcomment.dto.QCommentUpdate;
import com.backend.domain.question.domain.Question;
import com.backend.global.Audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qcomment_id")
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
//   member 구현후 해제    this.member = member;
    }


    public static QuestionComment toEntity(String content, Question question, Member member) {
        return QuestionComment.builder()
                .content(content)
                .question(question)
                .member(member)
                .build();
    }

    public QCommentResponse toResponseDto() {
        return QCommentResponse.builder()
                .qcommentId(id)
                .build();
    }

    public void patch(QCommentUpdate qCommentUpdate) {
        if(qCommentUpdate.getContent() != null)
            this.content = qCommentUpdate.getContent();
    }
}
