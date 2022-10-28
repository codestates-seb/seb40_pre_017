package com.backend.domain.acomment.dao;

import com.backend.domain.acomment.domain.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ACommentRepository extends JpaRepository<AnswerComment, Long> {
}
