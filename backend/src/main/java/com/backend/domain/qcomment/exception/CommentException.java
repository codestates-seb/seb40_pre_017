package com.backend.domain.qcomment.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;
import lombok.Getter;

public class CommentException extends BusinessException {
    @Getter
    private ErrorCode errorCode;

    public CommentException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}

