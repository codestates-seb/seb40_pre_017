package com.backend.domain.comment.application;

import com.backend.domain.comment.dao.QuestionCommentRepository;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.comment.dto.QuestionCommentCreate;
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




    public Long create(Long memberId, QuestionCommentCreate questionCommentCreate, Long questionId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);

        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        QuestionComment questionComment = QuestionComment.toEntity(questionCommentCreate.getContent(), question, member);

        QuestionComment savedComment = questionCommentRepository.save(questionComment);


        return savedComment.getId();

    }

    public Long update(Long memberId, QuestionCommentUpdate questionCommentUpdate, Long questionCommentId) {

        QuestionComment findComment = findVerifiedComment(questionCommentId);

        if(findComment.getMember().getId() == memberId) {
            findComment.patch(questionCommentUpdate);
        }else{
            throw new CommentException(ErrorCode.CANNOT_UPDATE_COMMENT);
        }


        return findComment.getId();

    }

    public Long delete(Long memberId, Long questionCommentId ) {

        QuestionComment findComment = findVerifiedComment(questionCommentId);

        if(findComment.getMember().getId() == memberId) {
            questionCommentRepository.delete(findComment);
        }else{
            throw new CommentException(ErrorCode.CANNOT_DELETE_COMMENT);
        }



        return findComment.getId();

    }


    private QuestionComment findVerifiedComment(Long questionCommentId) {
        Optional<QuestionComment> optionalComment = questionCommentRepository.findById(questionCommentId);
        QuestionComment findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
