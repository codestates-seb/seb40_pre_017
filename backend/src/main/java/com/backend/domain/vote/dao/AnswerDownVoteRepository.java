package com.backend.domain.vote.dao;


import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.AnswerUpVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDownVoteRepository extends JpaRepository<AnswerDownVote, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Answer_Down_Vote(answer_Id, member_Id, created_at) VALUES(:answerId, :memberId, now())", nativeQuery = true)
    void down(Long answerId, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM Answer_Down_Vote WHERE answer_Id = :answerId AND member_Id = :memberId", nativeQuery = true)
    int undoDown(Long answerId, Long memberId);
}
