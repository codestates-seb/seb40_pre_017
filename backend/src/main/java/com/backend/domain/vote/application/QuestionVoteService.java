package com.backend.domain.vote.application;


import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.vote.dao.QuestionDownVoteRepository;
import com.backend.domain.vote.dao.QuestionUpVoteRepository;
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
public class QuestionVoteService {

    private final QuestionUpVoteRepository questionUpVoteRepository;
    private final QuestionDownVoteRepository questionDownVoteRepository;

    private final MemberRepository memberRepository;

    public void up(Long questionId, Long memberId) {

        try {
            questionUpVoteRepository.up(questionId, memberId);
        }catch (DataIntegrityViolationException e) {
            log.error("handleDataIntegrityViolationException", e);
            throw new VoteException(ErrorCode.CONSTRAINTS_VIOLATED);
        }
    }

    public void undoUp(Long questionId, Long memberId) {
        questionUpVoteRepository.undoUp(questionId, memberId);

    }


    public void down(Long questionId, Long memberId) {
        try {
            questionDownVoteRepository.down(questionId, memberId);
        } catch (DataIntegrityViolationException e) {
            log.error("handleDataIntegrityViolationException", e);
            throw new VoteException(ErrorCode.CONSTRAINTS_VIOLATED);
        }

    }

    public void undoDown(Long questionId, Long memberId) {
        questionDownVoteRepository.undoDown(questionId, memberId);

    }
    
}
