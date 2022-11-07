package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenMalformed extends BusinessException {

    public TokenMalformed() {
        super(ErrorCode.TOKEN_MALFORMED.getMessage(), ErrorCode.TOKEN_MALFORMED);
    }
}
