package com.backend.domain.acomment.application;

import com.backend.domain.acomment.dao.ACommentRepository;
import com.backend.domain.acomment.domain.AnswerComment;
import com.backend.domain.acomment.dto.ACommentPatchDto;
import com.backend.domain.acomment.dto.ACommentPostDto;
import com.backend.domain.acomment.dto.ACommentResponseDto;
import com.backend.domain.acomment.exception.CommentException;
import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ACommentService {

    private final AnswerService answerService;

    private final ACommentRepository aCommentRepository;

    public ACommentService( AnswerService answerService, ACommentRepository aCommentRepository) {
        this.answerService = answerService;
        this.aCommentRepository = aCommentRepository;
    }

    public ACommentResponseDto createAComment(ACommentPostDto aCommentPostDto) {


        Answer answer = answerService.findVerifiedAnswer(aCommentPostDto.getAnswerId());


        //member 추가필요
        AnswerComment answerComment = AnswerComment.toEntity(aCommentPostDto.getContent(), answer);

        AnswerComment savedComment = aCommentRepository.save(answerComment);

        ACommentResponseDto result = savedComment.toResponseDto();

        return result;

    }

    public ACommentResponseDto updateAComment(ACommentPatchDto aCommentPatchDto) {

        AnswerComment findComment = findVerifiedComment(aCommentPatchDto.getAcommentId());

        findComment.patch(aCommentPatchDto);

        ACommentResponseDto result = findComment.toResponseDto();

        return result;



    }

    public void deleteAComment(Long acommentId) {

        AnswerComment findComment = findVerifiedComment(acommentId);

        aCommentRepository.delete(findComment);

    }


    private AnswerComment findVerifiedComment(Long acommentId) {
        Optional<AnswerComment> optionalComment = aCommentRepository.findById(acommentId);
        AnswerComment findComment = optionalComment.orElseThrow(() -> new CommentException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }
}
