package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerUpVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerUpVoteRepository extends JpaRepository<AnswerUpVote, Long>, AnswerUpVoteRepositoryCustom {


}
