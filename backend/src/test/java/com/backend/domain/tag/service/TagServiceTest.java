package com.backend.domain.tag.service;

import com.backend.domain.tag.domain.Tag;
import com.backend.domain.tag.dto.TagDto;
import com.backend.domain.tag.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
class TagServiceTest {
    @Autowired
    TagService tagService;
    @Autowired
    TagRepository tagRepository;


    @Test
    @DisplayName("태그가 db에 존재하면 저장하지않고 조회해온다.")
    public void addTag(){
        Tag dbJava = tagRepository.save(Tag.of("java"));
        Long dbJavaId = dbJava.getId();

        TagDto dtoJava = new TagDto("java");

        Tag tag = tagService.addTag(dtoJava);
        Long DtoJavaId = tag.getId();

        Assertions.assertThat(DtoJavaId).isEqualTo(dbJavaId);

    }

}