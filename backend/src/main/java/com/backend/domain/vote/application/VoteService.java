package com.backend.domain.vote.application;

import com.backend.domain.vote.dao.QuestionUpVoteRepositoryImpl;
import com.backend.domain.vote.dto.response.VoteStateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final QuestionUpVoteRepositoryImpl voteRepository;

    public VoteStateResponse getVotes(Long memberId, Long questionId){
        return voteRepository.getVotes(memberId,questionId);

    }

}
