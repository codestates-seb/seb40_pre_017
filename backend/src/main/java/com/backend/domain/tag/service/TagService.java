package com.backend.domain.tag.service;

import com.backend.domain.tag.TagNotFound;
import com.backend.domain.tag.domain.Tag;
import com.backend.domain.tag.dto.TagDto;
import com.backend.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /* 태그가 db에 존재하지 않아야 등록 */
    public Tag addTag(TagDto tagDto){
        Tag tag = tagDto.toEntity();

        if(!isTagExist(tag)){
            return tagRepository.save(tag);
        }

        return tagRepository.findByName(tag.getName()).orElseThrow(TagNotFound::new);
    }

    private boolean isTagExist(Tag tag) {
        return tagRepository.existsByName(tag.getName());
    }
}
