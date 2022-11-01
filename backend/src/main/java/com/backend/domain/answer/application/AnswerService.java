package com.backend.domain.answer.application;

import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerPatchDto;
import com.backend.domain.answer.dto.AnswerPostDto;
import com.backend.domain.answer.exception.AnswerException;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.exception.MemberNotFound;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Positive;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    public Long create(Long id,Long memberId, AnswerPostDto answerPostDto) {

        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);


        Answer answer = answerPostDto.toEntity(question,member);


        Answer savedAnswer = answerRepository.save(answer);


        return savedAnswer.getId();
    }

    public  Long update(@Positive Long answerId, Long memberId, AnswerPatchDto answerPatchDto) {


        Answer findAnswer = findVerifiedAnswer(answerId);

        if (memberId == findAnswer.getMember().getId()) {
            findAnswer.patch(answerPatchDto);
        }else{
            throw new AnswerException(ErrorCode.HANDLE_ACCESS_DENIED);
        }



        return findAnswer.getId();

    }


    public  Long delete(Long answerId, Long memberId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        if (memberId == findAnswer.getMember().getId()) {
            answerRepository.delete(findAnswer);
        }else{
            throw new AnswerException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        return findAnswer.getId();
    }

    public Long accept(Long id, Long answerId, Long memberId ) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);


        if(question.getMember().getId() == memberId) {
            findAnswer.accept();
            question.accept();
            if(findAnswer.getMember().getId() != memberId)
                findAnswer.getMember().answerAccepted();
        }else{
            throw new AnswerException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        return findAnswer.getId();

    }

    public Long unAccept(Long id, Long answerId, Long memberId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);


        if(question.getMember().getId() == memberId ) {
            findAnswer.unAccept();
            question.unAccept();
            if(findAnswer.getMember().getId() != memberId)
                findAnswer.getMember().answerUnAccepted();
        }else{
            throw new AnswerException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        return findAnswer.getId();

    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() -> new AnswerException(ErrorCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }



}
