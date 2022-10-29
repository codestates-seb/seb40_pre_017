package com.backend.domain.vote.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.vote.dto.AnswerVoteResponse;
import com.backend.domain.vote.dto.AnswerVoteUpdate;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="user_answer_unique",
                        columnNames = {"member_id","answer_id"}
                )
        }
)
public class AnswerVote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Answer answer;

    @Builder
    public AnswerVote( Member member, Answer answer) {
        this.count = 0L;
        this.member = member;
        this.answer = answer;
    }


    public AnswerVoteResponse toResponseDto() {

    }

    public void patch(AnswerVoteUpdate answerVoteUpdate) {

    }
}
