package com.backend.domain.question.dto.request;

import com.backend.domain.tag.dto.TagDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionUpdate {

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private  String title;

    @NotBlank
    @Size(min = 15, message = "15글자 이상 입력하세요")
    private  String content;

    private List<TagDto> tags;

    @Builder
    private QuestionUpdate(String title, String content, List<TagDto> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }




}
