package com.backend.domain.comment.dao;

import com.backend.domain.comment.domain.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
}
