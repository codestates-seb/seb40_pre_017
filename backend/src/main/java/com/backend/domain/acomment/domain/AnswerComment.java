package com.backend.domain.acomment.domain;

import com.backend.domain.acomment.dto.ACommentUpdate;
import com.backend.domain.acomment.dto.ACommentResponse;
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
    @Column(name = "acomment_id", nullable = false)
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
 //      멤버 구현후 해제 this.member = member;
    }

    /**
     * param member 추가 필요
     */
    public static AnswerComment toEntity(String content,Answer answer,Member member ) {
        return AnswerComment.builder()
                .content(content)
                .answer(answer)
                .member(member)
                .build();
    }
    public ACommentResponse toResponseDto() {
        return ACommentResponse.builder()
                .acommentId(id)
                .build();
    }

    public void patch(ACommentUpdate aCommentUpdate) {
        if(aCommentUpdate.getContent() != null)
            this.content = aCommentUpdate.getContent();
    }
}
