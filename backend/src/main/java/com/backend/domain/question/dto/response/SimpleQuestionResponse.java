package com.backend.domain.question.dto.response;

import com.backend.domain.question.domain.Question;
import com.backend.global.Constant;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleQuestionResponse {

    private Boolean isAnswered;
    private Long viewCount;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long questionId;
    private String link;
    private String title;
    private String summary;
    private Integer answerCount;

    @Builder
    public SimpleQuestionResponse(Boolean isAnswered, Long viewCount, LocalDateTime createAt, LocalDateTime modifiedAt, Long questionId, String link, String title, String summary, Integer answerCount) {
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.questionId = questionId;
        this.link = link;
        this.title = title;
        this.summary = summary;
        this.answerCount = answerCount;
    }


    public static SimpleQuestionResponse toResponse(Question question) {
        return SimpleQuestionResponse.builder()
                .viewCount(question.getView())
                .isAnswered(question.getIsAnswered())
                //votes
                .createAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .questionId(question.getId())
                .link(questionLink(question))
                .title(question.getTitle())
                .summary(getSummary(question.getContent()))
                .answerCount(question.getAnswers().size())
                .build();

    }

    private static String getSummary(String content) {
        if (content.length() > 255) {
            return content.substring(0, 255);
        }

        return content;
    }

    private static String questionLink(Question question) {
        return Constant.URL.getUrl().
                concat("/questions/").
                concat(String.valueOf(question.getId()));
    }

}