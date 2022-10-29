package com.backend.domain.comment.application;

import com.backend.domain.comment.dao.QuestionCommentRepository;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.comment.dto.QuestionCommentCreate;
import com.backend.domain.comment.dto.QuestionCommentResponse;
import com.backend.domain.comment.dto.QuestionCommentUpdate;
import com.backend.domain.comment.exception.CommentException;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.repository.MemberRepository;
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
public class QuestionCommentService {

    private final QuestionRepository questionRepository;

    private final QuestionCommentRepository questionCommentRepository;

    private final MemberRepository memberRepository;




    public QuestionCommentResponse createComment(Long memberId, QuestionCommentCreate questionCommentCreate, Long questionId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);


        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        QuestionComment questionComment = QuestionComment.toEntity(questionCommentCreate.getContent(), question, member);

        QuestionComment savedComment = questionCommentRepository.save(questionComment);

        QuestionCommentResponse result = savedComment.toResponseDto();
        return result;

    }

    public QuestionCommentResponse update(QuestionCommentUpdate questionCommentUpdate, Long questionCommentId) {

        QuestionComment findComment = findVerifiedComment(questionCommentId);

        findComment.patch(questionCommentUpdate);

        QuestionCommentResponse result = findComment.toResponseDto();

        return result;

    }

    public Long delete(Long questionCommentId) {

        QuestionComment findComment = findVerifiedComment(questionCommentId);

        questionCommentRepository.delete(findComment);

        return questionCommentId;

    }


    private QuestionComment findVerifiedComment(Long questionCommentId) {
        Optional<QuestionComment> optionalComment = questionCommentRepository.findById(questionCommentId);
        QuestionComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
