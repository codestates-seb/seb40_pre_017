package com.backend.domain.answer.exception;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "Answer not found");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }

}
