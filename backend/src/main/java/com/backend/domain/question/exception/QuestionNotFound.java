package com.backend.domain.question.exception;


import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class QuestionNotFound extends BusinessException {

    public QuestionNotFound() {
        super(ErrorCode.QUESTION_NOT_FOUND.getMessage(),ErrorCode.QUESTION_NOT_FOUND);
    }


}
