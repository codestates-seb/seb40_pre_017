package com.backend.domain.question.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class UploadFailed extends BusinessException {
	public UploadFailed() {
		super(ErrorCode.Upload_Failed.getMessage(), ErrorCode.Upload_Failed);
	}
}
