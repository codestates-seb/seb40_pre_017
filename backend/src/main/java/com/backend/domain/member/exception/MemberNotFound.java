package com.backend.domain.member.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class MemberNotFound extends BusinessException {

    public MemberNotFound() {
        super(ErrorCode.MEMBER_NOT_FOUND.getMessage(), ErrorCode.MEMBER_NOT_FOUND);
    }
}
