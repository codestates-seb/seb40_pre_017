package com.backend.domain.answer.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;
import lombok.Getter;

public class AnswerException extends BusinessException {

    @Getter
    private ErrorCode errorCode;

    public AnswerException(ErrorCode errorCode) {
        super(errorCode.getMessage(),errorCode);
        this.errorCode =errorCode;
    }
}
