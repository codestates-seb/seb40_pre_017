package com.backend.domain.comment.dto;

import com.backend.global.Constant;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleQuestionCommentResponse {


    private Long questionCommentId;
    private Long memberId;
    private String userName;
    private String link;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @Builder
    public SimpleQuestionCommentResponse(Long questionCommentId, Long memberId, String userName, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.questionCommentId = questionCommentId;
        this.memberId = memberId;
        this.userName = userName;
        this.link = memberLink(memberId);
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    private String memberLink(Long memberId) {
        return Constant.URL.getUrl().
                concat("/member/").
                concat(String.valueOf(memberId));
    }
}
