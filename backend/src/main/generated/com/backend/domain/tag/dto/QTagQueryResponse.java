package com.backend.domain.tag.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.domain.tag.dto.QTagQueryResponse is a Querydsl Projection type for TagQueryResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTagQueryResponse extends ConstructorExpression<TagQueryResponse> {

    private static final long serialVersionUID = -941003531L;

    public QTagQueryResponse(com.querydsl.core.types.Expression<Long> questionId, com.querydsl.core.types.Expression<String> tagName) {
        super(TagQueryResponse.class, new Class<?>[]{long.class, String.class}, questionId, tagName);
    }

}

