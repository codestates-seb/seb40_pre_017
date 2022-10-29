package com.backend.domain.comment.application;

import com.backend.domain.comment.dao.AnswerCommentRepository;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.comment.dto.AnswerCommentCreate;
import com.backend.domain.comment.dto.AnswerCommentResponse;
import com.backend.domain.comment.exception.CommentException;
import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerCommentService {

    private final AnswerService answerService;

    private final AnswerCommentRepository answerCommentRepository;

    public AnswerCommentResponse create(AnswerCommentCreate answerCommentCreate, Long answerId) {

        Answer answer = answerService.findVerifiedAnswer(answerId);

        // 토큰에서 회원정보 가져오는것으로 수정필요
        Member member = Member.builder()
                .email("abc@gmail.com")
                .password("1234")
                .username("hi")
                .build();

        AnswerComment answerComment = AnswerComment.toEntity(answerCommentCreate.getContent(), answer, member);

        AnswerComment savedComment = answerCommentRepository.save(answerComment);

        AnswerCommentResponse result = savedComment.toResponseDto();

        return result;

    }

    public AnswerCommentResponse update(AnswerCommentUpdate answerCommentUpdate, Long answerCommentId) {

        AnswerComment findComment = findVerifiedComment(answerCommentId);

        findComment.patch(answerCommentUpdate);

        AnswerCommentResponse result = findComment.toResponseDto();

        return result;

    }

    public Long delete(Long answerCommentId) {

        AnswerComment findComment = findVerifiedComment(answerCommentId);

        answerCommentRepository.delete(findComment);

        return answerCommentId;

    }


    private AnswerComment findVerifiedComment(Long answerCommentId) {
        Optional<AnswerComment> optionalComment = answerCommentRepository.findById(answerCommentId);
        AnswerComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
