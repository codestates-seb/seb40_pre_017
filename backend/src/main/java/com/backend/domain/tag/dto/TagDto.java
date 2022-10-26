package com.backend.domain.tag.dto;

import com.backend.domain.tag.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class TagDto {

    @NotBlank
    private String name;

    public TagDto(String name) {
        this.name = name;
    }

    public Tag toEntity(){
        return Tag.of(this.name);
    }
}
