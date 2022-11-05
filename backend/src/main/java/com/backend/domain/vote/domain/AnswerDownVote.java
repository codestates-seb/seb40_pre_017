package com.backend.domain.vote.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
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
                        name="member_answer_unique",
                        columnNames = {"member_id","answer_id"}
                )
        }
)
public class AnswerDownVote  {
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
    public AnswerDownVote(Member member, Answer answer) {
        this.member = member;
        this.answer = answer;
    }

    public static AnswerDownVote toEntity(Answer answer, Member member) {
        return  AnswerDownVote.builder()
                .answer(answer)
                .member(member)
                .build();

    }
}
