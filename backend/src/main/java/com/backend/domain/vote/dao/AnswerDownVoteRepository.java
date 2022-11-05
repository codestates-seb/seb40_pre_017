package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerDownVoteRepository extends JpaRepository<AnswerDownVote, Long>, AnswerDownVoteRepositoryCustom {

}
