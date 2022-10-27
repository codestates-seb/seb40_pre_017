package com.backend.domain.answer.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;
import lombok.Getter;

public class AnswerException extends BusinessException {

    public AnswerException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage(), ErrorCode.ANSWER_NOT_FOUND);
    }
}
