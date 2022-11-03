package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenUnsupported extends BusinessException {

    public TokenUnsupported() {
        super(ErrorCode.TOKEN_UNSUPPORTED.getMessage(), ErrorCode.TOKEN_UNSUPPORTED);
    }
}
