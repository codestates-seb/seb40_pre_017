package com.backend.domain.question.dto.request;



import com.backend.domain.tag.dto.TagDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@Getter
public class QuestionCreate {

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private final String title;

    @NotBlank
    @Size(min = 15, message = "30글자 이상 입력하세요")
    private final String content;

    private List<TagDto> tags;

    @Builder
    private QuestionCreate(String title, String content, List<TagDto> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }


}
