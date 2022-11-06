package com.backend.domain.vote.application;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.global.repository.MemberRepository;
import com.backend.domain.vote.dao.AnswerDownVoteRepository;
import com.backend.domain.vote.dao.AnswerUpVoteRepository;
import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.AnswerUpVote;
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

    private final AnswerService answerService;

    private final AnswerDownVoteRepository answerDownVoteRepository;

    private final MemberRepository memberRepository;



    public void up(Long answerId, Long memberId) {

        Answer answer = answerService.findVerifiedAnswer(answerId);
        Member answerWriter = answer.getMember();


        if(memberId != answerWriter.getId() ) {
            try {
                Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
                AnswerUpVote answerUpVote = AnswerUpVote.toEntity(answer, member);
                answerUpVoteRepository.up(answerUpVote);
                answerUpVoted(answerWriter);
            } catch (DataIntegrityViolationException e) {
                log.error("handleDataIntegrityViolationException", e);
                throw new VoteException(ErrorCode.ALREADY_VOTED);
            }
        }else{
            throw new VoteException(ErrorCode.CANNOT_VOTE_OWN_POST);
        }


    }

    public void undoUp(Long answerId,  Long memberId) {

        Member answerWriter = answerService.findVerifiedAnswer(answerId).getMember();

        Long result = answerUpVoteRepository.undoUp(answerId, memberId);
        if(result == 0) throw new VoteException(ErrorCode.VOTE_NOT_FOUND);

        undoAnswerUpVoted(answerWriter);


    }


    public void down(Long answerId,  Long memberId) {

        Answer answer = answerService.findVerifiedAnswer(answerId);
        Member answerWriter = answer.getMember();

        if(memberId != answerWriter.getId() ) {
            try {
                Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
                AnswerDownVote answerDownVote = AnswerDownVote.toEntity(answer, member);
                answerDownVoteRepository.down(answerDownVote);
                answerDownVoted(answerWriter);
            } catch (DataIntegrityViolationException e) {
                log.error("handleDataIntegrityViolationException", e);
                throw new VoteException(ErrorCode.ALREADY_VOTED);
            }
        } else{
            throw new VoteException(ErrorCode.CANNOT_VOTE_OWN_POST);
        }

    }

    public void undoDown(Long answerId,  Long memberId) {

        Member answerWriter = answerService.findVerifiedAnswer(answerId).getMember();
        Long result = answerDownVoteRepository.undoDown(answerId, memberId);
        if(result == 0) throw new VoteException(ErrorCode.VOTE_NOT_FOUND);

        undoAnswerDownVoted(answerWriter);
    }


    private void answerUpVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.answerUpVoted();
    }
    private void undoAnswerUpVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.undoAnswerUpVoted();
    }
    private void answerDownVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.answerDownVoted();
    }
    private void undoAnswerDownVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.undoAnswerDownVoted();
    }


}
