package com.backend.domain.qcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QCommentUpdate {

    @NotBlank(message = "컨텐츠는 공백이 아니어야 합니다.")
    private String content;

}
