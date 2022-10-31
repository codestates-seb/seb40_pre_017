package com.backend.domain.answer.dto;

import com.backend.domain.answer.domain.Answer;
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

    public static ComplexAnswerResponse of(Answer answer, List<SimpleAnswerCommentResponse> simpleAnswerCommentResponses){
        return ComplexAnswerResponse.builder()
                //답변정보
                .answerId(answer.getId())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .content(answer.getContent())
                .votes(0L)
                .isAccepted(answer.getIsAccepted())
                //질문 댓글 정보
                //작성자 정보
                .answerMember(MemberResponse.toResponse(answer.getMember()))
                //답변댓글 정보
                .simpleAnswerCommentResponses(simpleAnswerCommentResponses)
                .build();
    }
}
