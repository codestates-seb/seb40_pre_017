package com.backend.domain.member.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class UserNameDuplication extends BusinessException {

    public UserNameDuplication() {
        super(ErrorCode.USERNAME_DUPLICATION.getMessage(), ErrorCode.USERNAME_DUPLICATION);
    }
}
