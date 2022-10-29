package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
