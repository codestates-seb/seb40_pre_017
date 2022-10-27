package com.backend.domain.question.exception;


import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TitleDuplication extends BusinessException {
    public TitleDuplication() {
        super(ErrorCode.TITLE_DUPLICATION.getMessage(),ErrorCode.TITLE_DUPLICATION);
    }
}
