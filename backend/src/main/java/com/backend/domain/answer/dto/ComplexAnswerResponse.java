package com.backend.domain.answer.dto;

import com.backend.domain.comment.dto.SimpleAnswerCommentResponse;
import com.backend.domain.member.dto.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ComplexAnswerResponse {
    private Long answerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String content;
    private Long votes;
    private boolean isAccepted;
    private MemberResponse answerMember;
    private List<SimpleAnswerCommentResponse> answerComments;

    @Builder
    public ComplexAnswerResponse(Long answerId, LocalDateTime createdAt, LocalDateTime modifiedAt, String content, Long votes, boolean isAccepted, MemberResponse answerMember, List<SimpleAnswerCommentResponse> simpleAnswerCommentResponses) {
        this.answerId = answerId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.content = content;
        this.votes = votes;
        this.isAccepted = isAccepted;
        this.answerMember = answerMember;
        this.answerComments = simpleAnswerCommentResponses;
    }
}
