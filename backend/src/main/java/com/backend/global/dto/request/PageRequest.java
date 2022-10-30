package com.backend.global.dto.request;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest  {

    private static final int MAX_SIZE = 2000;

    private int page;
    private int size = 15;
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

//    public void setSize(int size) {
//        int DEFAULT_SIZE = 10;
//        int MAX_SIZE = 50;
//        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
//    }

//    public void setDirection(Sort.Direction direction) {
//        this.direction = direction;
//    }

    public long getOffset() {
        return (long) (Math.max(1,page)-1) *Math.min(size,MAX_SIZE);
    }
    // getter
    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page-1, size);
    }
}
