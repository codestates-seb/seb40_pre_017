package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenEmpty extends BusinessException {

    public TokenEmpty() {
        super(ErrorCode.TOKEN_ILLEGAL_ARGUMENT.getMessage(), ErrorCode.TOKEN_ILLEGAL_ARGUMENT);
    }
}
