package com.backend.domain.acomment.application;

import com.backend.domain.acomment.dao.ACommentRepository;
import com.backend.domain.acomment.domain.AnswerComment;
import com.backend.domain.acomment.dto.ACommentUpdate;
import com.backend.domain.acomment.dto.ACommentCreate;
import com.backend.domain.acomment.dto.ACommentResponse;
import com.backend.domain.acomment.exception.CommentException;
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
public class ACommentService {

    private final AnswerService answerService;

    private final ACommentRepository aCommentRepository;

    public ACommentResponse createComment(ACommentCreate aCommentCreate, Long answerId) {

        Answer answer = answerService.findVerifiedAnswer(answerId);

        Member member = Member.builder()
                .email("abc@gmail.com")
                .password("1234")
                .username("hi")
                .build();

        AnswerComment answerComment = AnswerComment.toEntity(aCommentCreate.getContent(), answer, member);

        AnswerComment savedComment = aCommentRepository.save(answerComment);

        ACommentResponse result = savedComment.toResponseDto();

        return result;

    }

    public ACommentResponse updateComment(ACommentUpdate aCommentUpdate, Long acommentId) {

        AnswerComment findComment = findVerifiedComment(acommentId);

        findComment.patch(aCommentUpdate);

        ACommentResponse result = findComment.toResponseDto();

        return result;

    }

    public Long deleteComment(Long acommentId) {

        AnswerComment findComment = findVerifiedComment(acommentId);

        aCommentRepository.delete(findComment);

        return acommentId;

    }


    private AnswerComment findVerifiedComment(Long acommentId) {
        Optional<AnswerComment> optionalComment = aCommentRepository.findById(acommentId);
        AnswerComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }
}
