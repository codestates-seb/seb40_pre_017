package com.backend.domain.refreshtoken.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TokenSignatureInvalid extends BusinessException {

    public TokenSignatureInvalid() {
        super(ErrorCode.TOKEN_SIGNATURE_INVALID.getMessage(), ErrorCode.TOKEN_SIGNATURE_INVALID);
    }
}
