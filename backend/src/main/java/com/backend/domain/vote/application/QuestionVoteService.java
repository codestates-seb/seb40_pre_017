package com.backend.domain.vote.application;


import com.backend.domain.member.domain.Member;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.vote.dao.QuestionDownVoteRepository;
import com.backend.domain.vote.dao.QuestionUpVoteRepository;
import com.backend.domain.vote.domain.QuestionDownVote;
import com.backend.domain.vote.domain.QuestionUpVote;
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

    private final QuestionRepository questionRepository;

    private final MemberRepository memberRepository;


    public void up(Long questionId, Long memberId) {


        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);
        Member questionWriter =question.getMember();


        if (memberId != questionWriter.getId()) {
            try {
                Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
                QuestionUpVote questionUpVote = QuestionUpVote.toEntity(member, question);
                questionUpVoteRepository.up(questionUpVote);
                questionUpVoted(questionWriter);
            } catch (DataIntegrityViolationException e) {
                log.error("handleDataIntegrityViolationException", e);
                throw new VoteException(ErrorCode.ALREADY_VOTED);
            }
        } else {
            throw new VoteException(ErrorCode.CANNOT_VOTE_OWN_POST);
        }
    }

    public void undoUp(Long questionId, Long memberId) {

        Member questionWriter = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new).getMember();

        Long result = questionUpVoteRepository.undoUp(questionId, memberId);

        if(result == 0) throw new VoteException(ErrorCode.VOTE_NOT_FOUND);
       undoQuestionUpVoted(questionWriter);

    }


    public void down(Long questionId, Long memberId) {

        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);
        Member questionWriter =question.getMember();

        if (memberId != questionWriter.getId()) {
            try {
                Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);
                QuestionDownVote questionDownVote = QuestionDownVote.toEntity(member, question);
                questionDownVoteRepository.down(questionDownVote);
                questionDownVoted(questionWriter);
            } catch (DataIntegrityViolationException e) {
                log.error("handleDataIntegrityViolationException", e);
                throw new VoteException(ErrorCode.ALREADY_VOTED);
            }
        }else{
            throw new VoteException(ErrorCode.CANNOT_VOTE_OWN_POST);
        }
    }




    public void undoDown(Long questionId, Long memberId) {

        Member questionWriter = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new).getMember();

        Long result = questionDownVoteRepository.undoDown(questionId, memberId);
        if(result == 0) throw new VoteException(ErrorCode.VOTE_NOT_FOUND);

        undoQuestionDownVoted(questionWriter);

    }


    private void questionUpVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.questionUpVoted();
    }


    private void questionDownVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.questionDownVoted();
    }

    private void undoQuestionUpVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.undoQuestionUpVoted();
    }

    private void undoQuestionDownVoted(Member member) {
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new);
        findMember.undoQuestionDownVoted();
    }


}
