package com.backend.domain.question.exception;


public class TitleDuplicate extends RuntimeException{
    public TitleDuplicate() {
        super("같은 제목의 질문이 존재합니다.");
    }
}
