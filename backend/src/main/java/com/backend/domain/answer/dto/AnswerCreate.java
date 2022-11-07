package com.backend.domain.answer.dto;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreate {

    @NotBlank(message = "컨텐츠는 공백이 아니어야 합니다.")
    private String content;

    public  Answer toEntity(Question question, Member member) {
        return Answer.builder()
                .content(content)
                .isAccepted(false)
                .question(question)
                .member(member)
                .build();
    }

}
