package com.backend.domain.question.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class NoImage extends BusinessException {
	public NoImage() {
		super(ErrorCode.No_Image.getMessage(),ErrorCode.No_Image);
	}
}
