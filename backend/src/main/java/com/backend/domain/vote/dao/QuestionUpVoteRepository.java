package com.backend.domain.vote.dao;

import com.backend.domain.vote.domain.QuestionUpVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionUpVoteRepository extends JpaRepository<QuestionUpVote, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Question_Up_Vote(question_Id, member_Id,created_at) VALUES(:questionId, :memberId, now())", nativeQuery = true)
    void up(Long questionId, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM Question_Up_Vote WHERE question_Id = :questionId AND member_Id = :memberId", nativeQuery = true)
    void undoUp(Long questionId, Long memberId);

}
