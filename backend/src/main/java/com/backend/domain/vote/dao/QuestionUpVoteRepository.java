package com.backend.domain.vote.dao;

import com.backend.domain.vote.domain.QuestionUpVote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionUpVoteRepository extends JpaRepository<QuestionUpVote,Long>, QuestionUpVoteRepositoryCustom {


}
