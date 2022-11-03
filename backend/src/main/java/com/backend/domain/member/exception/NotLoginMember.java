package com.backend.domain.member.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class NotLoginMember extends BusinessException {

    public NotLoginMember() {
        super(ErrorCode.NOT_LOGIN_MEMBER.getMessage(), ErrorCode.NOT_LOGIN_MEMBER);
    }
}
