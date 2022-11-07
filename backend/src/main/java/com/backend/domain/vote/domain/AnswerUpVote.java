package com.backend.domain.vote.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="user_answer_uq",
                        columnNames = {"member_id","answer_id"}
                )
        }
)
public class AnswerUpVote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;
@Builder
    public AnswerUpVote(Member member, Answer answer) {
        this.member = member;
        this.answer = answer;
    }


    public static AnswerUpVote toEntity(Answer answer, Member member) {
        return AnswerUpVote.builder()
                .answer(answer)
                .member(member)
                .build();
    }
}
