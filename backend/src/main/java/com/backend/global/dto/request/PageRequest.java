package com.backend.global.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public final class PageRequest {

    private static final int MAX_SIZE = 2000;



    private int page;
    private int size = 15;
    private Sort.Direction sort;
    private String filters;
    private List<Filter> filterEnums;


    public PageRequest(int page, int size, Sort.Direction sort, String filters) {
        this.page = page <= 0 ? 1 : page;
        this.size = size;
        this.sort = sort;
        this.filters = filters;
        this.filterEnums= Arrays.stream(filters.split(",")).map(Filter::valueOf).collect(Collectors.toList());
    }

    public long getOffset() {

        return (long) (Math.max(1,page)-1) *Math.min(size,MAX_SIZE);
    }


    // getter
    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page-1, size);
    }


    @Getter
    public enum Filter{
        NoAnswer,
        NoAcceptedAnswer;


    }

}

