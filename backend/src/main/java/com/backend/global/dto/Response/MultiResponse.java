package com.backend.global.dto.Response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponse<T> {
    private List<T> items;
    private PageInfo pageInfo;


    private MultiResponse(Page<T> items) {
        this.items = items.getContent();
        this.pageInfo = PageInfo.builder()
                .page(items.getNumber()+1)
                .size(items.getSize())
                .totalElements(items.getTotalElements())
                .totalPages(items.getTotalPages())
                .build();
    }

    public static MultiResponse<?> of(Page<?> items){
        return new MultiResponse<>(items);

    }

    @Getter
    static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

        @Builder
        public PageInfo(int page, int size, long totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }



    }

}
