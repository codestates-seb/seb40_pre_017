package com.backend.domain.question.exception;


public class QuestionNotFound extends RuntimeException{

    public QuestionNotFound() {
        super("질문을 찾을 수 없습니다.");
    }
}
