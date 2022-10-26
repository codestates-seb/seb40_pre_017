package com.backend.domain.question.dto;


import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.domain.QuestionTag;
import com.backend.domain.tag.domain.Tag;
import com.backend.domain.tag.dto.TagDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class QuestionCreateDto {

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private final String title;

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private final String content;

    private ArrayList<TagDto> tags;

    @Builder
    private QuestionCreateDto(String title, String content, ArrayList<TagDto> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }


}
