package com.backend.domain.answer.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class AnswerException extends BusinessException {

    public AnswerException(ErrorCode errorCode) {
        super(errorCode.getMessage(), ErrorCode.ANSWER_NOT_FOUND);
    }
}
