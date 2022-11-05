package com.backend.domain.vote.domain;

import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
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
                        name="user_question_uq",
                        columnNames = {"member_id","question_id"}
                )
        }
)
public class QuestionUpVote  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionUpVote(Member member, Question question) {
        this.member = member;
        this.question = question;
    }

    public static QuestionUpVote toEntity(Member member, Question question) {
        return QuestionUpVote.builder()
                .member(member)
                .question(question)
                .build();
    }
}
