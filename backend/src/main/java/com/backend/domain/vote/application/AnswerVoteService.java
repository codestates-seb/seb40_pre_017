package com.backend.domain.vote.application;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.vote.dao.AnswerVoteRepository;
import com.backend.domain.vote.domain.AnswerVote;
import com.backend.domain.vote.dto.AnswerVoteResponse;
import com.backend.domain.vote.dto.AnswerVoteUpdate;
import com.backend.domain.vote.exception.VoteException;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerService answerService;

    private final AnswerVoteRepository answerVoteRepository;

    public AnswerVoteResponse up(Long answerId) {

        Answer findAnswer = answerService.findVerifiedAnswer(answerId);
        findAnswer.get


        AnswerVote savedComment = answerVoteRepository.save(answerVote);

        return result;

    }

    public AnswerVoteResponse undoUp(AnswerVoteUpdate answerVoteUpdate, Long answerVoteId) {

        AnswerVote findComment = findVerifiedComment(answerVoteId);

        findComment.patch(answerVoteUpdate);

        AnswerVoteResponse result = findComment.toResponseDto();

        return result;

    }

    private AnswerVote findVerifiedComment(Long answerVoteId) {
        Optional<AnswerVote> optionalComment = answerVoteRepository.findById(answerVoteId);
        AnswerVote findComment = optionalComment.orElseThrow(() -> new VoteException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
