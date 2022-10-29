package com.backend.domain.vote.application;


import com.backend.domain.comment.exception.CommentException;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.exception.QuestionNotFound;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.vote.dao.QuestionVoteRepository;
import com.backend.domain.vote.domain.QuestionVote;
import com.backend.domain.vote.dto.QuestionVoteCreate;
import com.backend.domain.vote.dto.QuestionVoteResponse;
import com.backend.domain.vote.dto.QuestionVoteUpdate;
import com.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionRepository questionRepository;

    private final QuestionVoteRepository questionVoteRepository;



    public QuestionVoteResponse create(QuestionVoteCreate questionVoteCreate, Long questionId) {

        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        // 토큰에서 회원정보 가져오는것으로 수정필요
        Member member = Member.builder()
                .email("abc@gmail.com")
                .password("1234")
                .username("hi")
                .build();

        QuestionVote questionVote = QuestionVote.toEntity(questionVoteCreate.getContent(), question, member);

        QuestionVote savedComment = questionVoteRepository.save(questionVote);

        QuestionVoteResponse result = savedComment.toResponseDto();
        return result;

    }

    public QuestionVoteResponse update(QuestionVoteUpdate questionVoteUpdate, Long questionVoteId) {

        QuestionVote findComment = findVerifiedComment(questionVoteId);

        findComment.patch(questionVoteUpdate);

        QuestionVoteResponse result = findComment.toResponseDto();

        return result;

    }

    public Long delete(Long questionVoteId) {

        QuestionVote findComment = findVerifiedComment(questionVoteId);

        questionVoteRepository.delete(findComment);

        return questionVoteId;

    }


    private QuestionVote findVerifiedComment(Long questionVoteId) {
        Optional<QuestionVote> optionalComment = questionVoteRepository.findById(questionVoteId);
        QuestionVote findComment = optionalComment.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
        return findComment;
    }


}
