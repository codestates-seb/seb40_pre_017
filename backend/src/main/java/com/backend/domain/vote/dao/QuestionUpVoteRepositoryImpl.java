package com.backend.domain.vote.dao;


import com.backend.domain.member.domain.QMember;
import com.backend.domain.vote.dto.projection.QVoteCountProjection;
import com.backend.domain.vote.dto.projection.VoteCountProjection;
import com.backend.domain.vote.dto.response.VoteStateResponse;
import com.backend.domain.vote.exception.NoneStillVoted;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.backend.domain.answer.domain.QAnswer.*;
import static com.backend.domain.question.domain.QQuestion.question;
import static com.backend.domain.vote.domain.QAnswerDownVote.answerDownVote;
import static com.backend.domain.vote.domain.QAnswerUpVote.answerUpVote;
import static com.backend.domain.vote.domain.QQuestionDownVote.questionDownVote;
import static com.backend.domain.vote.domain.QQuestionUpVote.questionUpVote;

@RequiredArgsConstructor
@Slf4j
@Repository
public class QuestionUpVoteRepositoryImpl implements QuestionUpVoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public VoteStateResponse getVotes(Long memberId, Long questionId) {

        List<VoteCountProjection> voteCountProjections = jpaQueryFactory.select(new QVoteCountProjection(

                        Expressions.asNumber(questionId).as("question_id"),
                        answer.id,
                        questionUpVote.id,
                        questionDownVote.id,
                        answerUpVote.id,
                        answerDownVote.id)
                )
                .from(question)
                .leftJoin(answer)
                .on(question.id.eq(answer.question.id))
                .leftJoin(questionUpVote)
                .on(question.id.eq(questionUpVote.question.id))
                .leftJoin(questionDownVote)
                .on(question.id.eq(questionDownVote.question.id))
                .leftJoin(answerDownVote)
                .on(answer.id.eq(answerDownVote.answer.id))
                .leftJoin(answerUpVote)
                .on(answer.id.eq(answerUpVote.answer.id))
                .where(questionUpVote.member.id.eq(memberId)
                                .or(questionDownVote.member.id.eq(memberId))
                                .or(answerUpVote.member.id.eq(memberId))
                                .or(answerDownVote.member.id.eq(memberId))
                ).fetch();

        if (voteCountProjections.size()==0) {
            throw new NoneStillVoted();
        }


        VoteStateResponse.VoteStateResponseBuilder voteStateResponseBuilder = VoteStateResponse.builder();

        Optional<Long> questionUpVoteCount = Optional.ofNullable(voteCountProjections.get(0).getQuestionUpVoteCount());

        voteStateResponseBuilder.questionUpVote(questionUpVoteCount.isPresent());

        Optional<Long> questionDownVoteCount = Optional.ofNullable(voteCountProjections.get(0).getQuestionDownVoteCount());

        voteStateResponseBuilder.questionDownVote(questionDownVoteCount.isPresent());


        List<VoteStateResponse.AnswerVoteState> answerVoteStates = new ArrayList<>();

        for (VoteCountProjection voteCountProjection : voteCountProjections) {
            /**
             *  질문 1 회원2 질문찬성
             *  질문  답변아이디 질찬  질반   답찬    답반
             *   1    null    1   null null null
             */

            Optional.ofNullable(voteCountProjection.getAnswerId()).ifPresent(aLong -> {
                        Boolean AnswerUpVoteStatus = Optional.ofNullable(voteCountProjection.getAnswerUpVoteCount()).isPresent();
                        Boolean AnswerDownVoteStatus = Optional.ofNullable(voteCountProjection.getAnswerDownVoteCount()).isPresent();
                        answerVoteStates.add(VoteStateResponse.AnswerVoteState.builder()
                                .answerId(voteCountProjection.getAnswerId())
                                .answerUpVote(AnswerUpVoteStatus)
                                .answerDownVote(AnswerDownVoteStatus)
                                .build());
                    }
            );

        }
        VoteStateResponse voteStateResponse = voteStateResponseBuilder.answerVoteStates(answerVoteStates).build();
        return voteStateResponse;
        }

    @Override
    public Long questionVoteUndoUp(Long questionId, Long memberId) {
        return jpaQueryFactory.delete(questionUpVote)
            .where(questionUpVote.question.id.eq(questionId)
                .and(questionUpVote.member.id.eq(memberId))
            ).execute();
    }

}



