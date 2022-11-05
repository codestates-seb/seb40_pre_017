package com.backend.domain.vote.dao;

import com.backend.domain.vote.domain.QuestionDownVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDownVoteRepository extends JpaRepository<QuestionDownVote, Long>, QuestionDownVoteRepositoryCustom {


}
