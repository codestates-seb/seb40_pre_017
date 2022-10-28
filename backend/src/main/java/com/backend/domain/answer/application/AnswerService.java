package com.backend.domain.answer.application;

import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerPatchDto;
import com.backend.domain.answer.dto.AnswerPostDto;
import com.backend.domain.answer.dto.AnswerResponseDto;
import com.backend.domain.answer.exception.AnswerException;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerResponseDto createAnswer(AnswerPostDto answerPostDto) {

        Answer answer = answerPostDto.toEntity();

        Answer savedAnswer = answerRepository.save(answer);

        AnswerResponseDto result = savedAnswer.toResponseDto();

        return result;
    }

    public  AnswerResponseDto updateAnswer(AnswerPatchDto answerPatchDto) {
        Answer findAnswer = findVerifiedAnswer(answerPatchDto.getAnswerId());

        findAnswer.patch(answerPatchDto);
        AnswerResponseDto result = findAnswer.toResponseDto();

        return result;

    }


    public  void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);

    }

    public void acceptAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        findAnswer.accept();

    }

    public void unAcceptAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        findAnswer.unAccept();
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() -> new AnswerException(ErrorCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

}
