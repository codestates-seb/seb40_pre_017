package com.backend.domain.vote.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class NoneStillVoted extends BusinessException {
    public NoneStillVoted() {
        super(ErrorCode.NONE_STILL_VOTED.getMessage(), ErrorCode.NONE_STILL_VOTED);
    }
}
