package com.backend.domain.qcomment.dao;

import com.backend.domain.qcomment.domain.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QCommentRepository extends JpaRepository<QuestionComment, Long> {
}
