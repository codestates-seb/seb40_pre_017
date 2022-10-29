package com.backend.domain.comment.dto;

import com.backend.domain.member.domain.Member;
import com.backend.global.Constant;
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


    private String memberLink(Long memberId) {
        return Constant.URL.getUrl().
                concat("/member/").
                concat(String.valueOf(memberId));
    }
}
