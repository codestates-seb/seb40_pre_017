package com.backend.domain.answer.exception;

import lombok.Getter;

public class AnswerException extends RuntimeException{
    @Getter
    private ExceptionCode exceptionCode;

    public AnswerException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
