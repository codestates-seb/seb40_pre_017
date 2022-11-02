package com.backend.domain.comment.application;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.comment.dao.AnswerCommentRepository;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.comment.dto.AnswerCommentCreate;
import com.backend.domain.comment.dto.AnswerCommentUpdate;
import com.backend.domain.comment.exception.CommentException;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.repository.MemberRepository;
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


    private final MemberRepository memberRepository;
    public Long create(Long memberId, Long answerId, AnswerCommentCreate answerCommentCreate ) {


        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
        Answer answer = answerService.findVerifiedAnswer(answerId);

        AnswerComment answerComment = AnswerComment.toEntity(answerCommentCreate.getContent(), answer, member);

        AnswerComment savedComment = answerCommentRepository.save(answerComment);


        return savedComment.getId();

    }

    public Long update(Long memberId,  Long answerCommentId , AnswerCommentUpdate answerCommentUpdate) {

        AnswerComment findComment = findVerifiedComment(answerCommentId);

        if(findComment.getMember().getId() == memberId) {
            findComment.patch(answerCommentUpdate);
        }else{
            throw new CommentException(ErrorCode.CANNOT_UPDATE_COMMENT);
        }

        return findComment.getId();

    }

    public Long delete(Long memberId, Long answerCommentId) {

        AnswerComment findComment = findVerifiedComment(answerCommentId);

        if(findComment.getMember().getId() == memberId) {
            answerCommentRepository.delete(findComment);
        }else{
            throw new CommentException(ErrorCode.CANNOT_DELETE_COMMENT);
        }

        return findComment.getId();

    }


    private AnswerComment findVerifiedComment(Long answerCommentId) {
        Optional<AnswerComment> optionalComment = answerCommentRepository.findById(answerCommentId);
        AnswerComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
