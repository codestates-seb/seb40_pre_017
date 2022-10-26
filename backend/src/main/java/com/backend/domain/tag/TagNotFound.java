package com.backend.domain.tag;


public class TagNotFound extends RuntimeException{
    public TagNotFound() {
        super("태그가 없습니다");
    }
}
