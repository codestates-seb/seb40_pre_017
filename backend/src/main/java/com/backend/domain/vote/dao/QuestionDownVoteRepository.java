package com.backend.domain.vote.dao;

import com.backend.domain.vote.domain.QuestionDownVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDownVoteRepository extends JpaRepository<QuestionDownVote, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Question_Down_Vote(question_Id, member_Id,created_at) VALUES(:questionId, :memberId, now())", nativeQuery = true)
    void down(Long questionId, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM Question_Down_Vote WHERE question_Id = :questionId AND member_Id = :memberId", nativeQuery = true)
    int undoDown(Long questionId, Long memberId);
}
