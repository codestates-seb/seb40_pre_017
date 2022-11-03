package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenInvalid extends BusinessException {

    public TokenInvalid() {
        super(ErrorCode.TOKEN_INVALID.getMessage(), ErrorCode.TOKEN_INVALID);
    }
}
