package com.backend.domain.answer.application;

import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerPatchDto;
import com.backend.domain.answer.dto.AnswerPostDto;
import com.backend.domain.answer.dto.AnswerResponseDto;
import com.backend.domain.answer.exception.AnswerException;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
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
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    public Long createAnswer(Long id,Long memberId, AnswerPostDto answerPostDto) {

        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        Member member = memberRepository.findById(id).orElseThrow(QuestionNotFound::new);


        Answer answer = answerPostDto.toEntity(question,getMember());


        Answer savedAnswer = answerRepository.save(answer);

//        AnswerResponseDto result = savedAnswer.toResponseDto();

        return savedAnswer.getId();
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

    private Member getMember() {
        Member member = Member.builder()
                .email("thsdf0@naver.com")
                .password("asdf1234")
                .profileImage("ssdafasldkfj")
                .reputation(0L)
                .username("thwsadf0")
                .build();
        return member;
    }

}
