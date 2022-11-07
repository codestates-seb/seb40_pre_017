package com.backend.domain.tag.exception;


import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class TagNotFound extends BusinessException {
    public TagNotFound() {
        super(ErrorCode.TAG_NOT_FOUND.getMessage(), ErrorCode.TAG_NOT_FOUND);
    }
}
