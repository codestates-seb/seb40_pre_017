package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
