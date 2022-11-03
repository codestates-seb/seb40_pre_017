package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenExpired extends BusinessException {

    public TokenExpired() {
        super(ErrorCode.TOKEN_EXPIRED.getMessage(), ErrorCode.TOKEN_EXPIRED);
    }
}
