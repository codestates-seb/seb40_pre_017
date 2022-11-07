package com.backend.domain.tag.exception;

import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.ErrorCode;

public class ContainsNotExistentTags extends BusinessException {
    public ContainsNotExistentTags() {
        super(ErrorCode.CONTAINS_NON_EXISTENT_TAGS.getMessage(), ErrorCode.CONTAINS_NON_EXISTENT_TAGS);
}

}
