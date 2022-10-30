package com.backend.domain.comment.dto;

import com.backend.domain.comment.domain.AnswerComment;
import com.backend.global.util.Constant;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleAnswerCommentResponse {
    private Long answerCommentId;
    private Long memberId;
    private String userName;
    private String link;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    @Builder
    public SimpleAnswerCommentResponse(Long answerCommentId, Long memberId, String userName, String content, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.answerCommentId = answerCommentId;
        this.memberId = memberId;
        this.userName = userName;
        this.link = memberLink(memberId);
        this.content = content;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public static SimpleAnswerCommentResponse of(AnswerComment answerComment) {
        return SimpleAnswerCommentResponse.builder()
                .answerCommentId(answerComment.getId())
                .memberId(answerComment.getMember().getId())
                .userName(answerComment.getMember().getUsername())
                .content(answerComment.getContent())
                .createAt(answerComment.getCreatedAt())
                .modifiedAt(answerComment.getModifiedAt())
                .build();
    }




    private String memberLink(Long memberId) {
        return Constant.URL.getUrl().
                concat("/member/").
                concat(String.valueOf(memberId));
    }
}
