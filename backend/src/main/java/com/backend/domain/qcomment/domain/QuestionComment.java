package com.backend.domain.qcomment.domain;

import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.global.Audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QuestionComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qcomment_id", nullable = false)
    private Long Id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionComment(String content) {
        this.content = content;
    }


}
