package com.backend.domain.qcomment.application;

import com.backend.domain.member.domain.Member;
import com.backend.domain.qcomment.dao.QCommentRepository;
import com.backend.domain.qcomment.domain.QuestionComment;
import com.backend.domain.qcomment.dto.QCommentCreate;
import com.backend.domain.qcomment.dto.QCommentResponse;
import com.backend.domain.qcomment.dto.QCommentUpdate;
import com.backend.domain.qcomment.exception.CommentException;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QCommentService {

    private final QuestionRepository questionRepository;

    private final QCommentRepository qCommentRepository;



    public QCommentResponse createComment(QCommentCreate qCommentCreate, Long questionId) {

        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        // 토큰에서 회원정보 가져오는것으로 수정필요
        Member member = Member.builder()
                .email("abc@gmail.com")
                .password("1234")
                .username("hi")
                .build();

        QuestionComment questionComment = QuestionComment.toEntity(qCommentCreate.getContent(), question, member);

        QuestionComment savedComment = qCommentRepository.save(questionComment);

        QCommentResponse result = savedComment.toResponseDto();
        return result;

    }

    public QCommentResponse updateComment(QCommentUpdate qCommentUpdate, Long qcommentId) {

        QuestionComment findComment = findVerifiedComment(qcommentId);

        findComment.patch(qCommentUpdate);

        QCommentResponse result = findComment.toResponseDto();

        return result;

    }

    public Long deleteComment(Long qcommentId) {

        QuestionComment findComment = findVerifiedComment(qcommentId);

        qCommentRepository.delete(findComment);

        return qcommentId;

    }


    private QuestionComment findVerifiedComment(Long qcommentId) {
        Optional<QuestionComment> optionalComment = qCommentRepository.findById(qcommentId);
        QuestionComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
