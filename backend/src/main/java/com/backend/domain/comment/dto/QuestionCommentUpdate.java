package com.backend.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCommentUpdate {

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private String content;

}
