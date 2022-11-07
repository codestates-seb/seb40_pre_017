package com.backend.domain.refreshtoken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefreshToken is a Querydsl query type for RefreshToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefreshToken extends EntityPathBase<RefreshToken> {

    private static final long serialVersionUID = -1980196069L;

    public static final QRefreshToken refreshToken = new QRefreshToken("refreshToken");

    public final com.backend.global.Audit.QAuditable _super = new com.backend.global.Audit.QAuditable(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> key = createNumber("key", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath value = createString("value");

    public QRefreshToken(String variable) {
        super(RefreshToken.class, forVariable(variable));
    }

    public QRefreshToken(Path<? extends RefreshToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefreshToken(PathMetadata metadata) {
        super(RefreshToken.class, metadata);
    }

}

