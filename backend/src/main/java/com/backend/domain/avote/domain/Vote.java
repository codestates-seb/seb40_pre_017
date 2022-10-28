package com.backend.domain.avote.domain;

import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Question question;


}
