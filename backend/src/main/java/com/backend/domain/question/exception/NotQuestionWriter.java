package com.backend.domain.question.exception;


import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class NotQuestionWriter extends BusinessException {

    public NotQuestionWriter() {
        super(ErrorCode.NOT_QUESTION_WRITER.getMessage(),ErrorCode.NOT_QUESTION_WRITER);
    }


}
