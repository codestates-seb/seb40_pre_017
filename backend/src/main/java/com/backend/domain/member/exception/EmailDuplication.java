package com.backend.domain.member.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class EmailDuplication extends BusinessException {

    public EmailDuplication() {
        super(ErrorCode.EMAIL_DUPLICATION.getMessage(), ErrorCode.EMAIL_DUPLICATION);
    }
}
