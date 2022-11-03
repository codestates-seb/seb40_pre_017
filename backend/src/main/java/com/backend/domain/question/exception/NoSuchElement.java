package com.backend.domain.question.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class NoSuchElement extends BusinessException {


    public NoSuchElement() {
        super(ErrorCode.NO_SUCH_ELEMENT.getMessage(), ErrorCode.NO_SUCH_ELEMENT);
    }
}
