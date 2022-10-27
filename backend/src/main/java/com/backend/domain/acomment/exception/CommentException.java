package com.backend.domain.acomment.exception;

import com.backend.domain.answer.exception.ExceptionCode;
import lombok.Getter;

public class CommentException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public CommentException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
