package com.backend.domain.vote.application;

import com.backend.domain.vote.dao.QuestionUpVoteRepository;
import com.backend.domain.vote.dao.VoteRepository;
import com.backend.domain.vote.dto.response.VoteStateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteStateResponse getVotes(Long memberId, Long questionId){
        return voteRepository.getVotes(memberId,questionId);

    }

}
