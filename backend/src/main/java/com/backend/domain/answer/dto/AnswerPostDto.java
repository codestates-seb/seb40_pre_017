package com.backend.domain.answer.dto;

import com.backend.domain.answer.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPostDto {

    @NotBlank(message = "컨텐츠는 공백이 아니어야 합니다.")
    private String content;

    public  Answer toEntity() {
        return Answer.builder()
                .content(content)
                .isAccepted(false)
                .build();
    }

}
