package com.backend.domain.vote.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;
import lombok.Getter;

public class VoteException extends BusinessException {
    @Getter
    private ErrorCode errorCode;

    public VoteException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.errorCode = errorCode;
    }
}

