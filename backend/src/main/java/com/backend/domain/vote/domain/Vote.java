package com.backend.domain.vote.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long voteId;

}
