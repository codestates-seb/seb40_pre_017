package com.backend.domain.vote.application;

import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.vote.dao.AnswerDownVoteRepository;
import com.backend.domain.vote.dao.AnswerUpVoteRepository;
import com.backend.domain.vote.exception.VoteException;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnswerVoteService {
    private final AnswerUpVoteRepository answerUpVoteRepository;

    private final AnswerDownVoteRepository answerDownVoteRepository;

    private final MemberRepository memberRepository;

    public void up(Long answerId, Long memberId) {

        try {
            answerUpVoteRepository.up(answerId, memberId);
        } catch (DataIntegrityViolationException e) {
            log.error("handleDataIntegrityViolationException", e);
            throw new VoteException(ErrorCode.CONSTRAINTS_VIOLATED);
        }


    }

    public void undoUp(Long answerId, Long memberId) {
        answerUpVoteRepository.undoUp(answerId, memberId);

    }


    public void down(Long answerId, Long memberId) {
        try {
            answerDownVoteRepository.down(answerId, memberId);

        }catch (DataIntegrityViolationException e) {
            log.error("handleDataIntegrityViolationException", e);
            throw new VoteException(ErrorCode.CONSTRAINTS_VIOLATED);
        }


        Long reputation = memberRepository.findById(memberId).get().getReputation();
        reputation -= 1;

    }

    public void undoDown(Long answerId, Long memberId) {
        answerDownVoteRepository.undoDown(answerId, memberId);

        Long reputation = memberRepository.findById(memberId).get().getReputation();
        reputation += 1;

    }
}
