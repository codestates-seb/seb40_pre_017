package com.backend.domain.vote.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.vote.dto.QuestionVoteResponse;
import com.backend.domain.vote.dto.QuestionVoteUpdate;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionVote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @Column(nullable = false)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Question question;

    @Builder
    public QuestionVote(Long count, Member member, Question question) {
        this.count = count;
        this.member = member;
        this.question = question;
    }

    public static QuestionVote toEntity(Question question, Member member) {
        return QuestionVote.builder()
                .count(0L)
                .question(question)
                .member(member)
                .build();

    }

    public QuestionVoteResponse toResponseDto() {

    }

    public void patch(QuestionVoteUpdate questionVoteUpdate) {

    }
}
