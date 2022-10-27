package com.backend.domain.refreshtoken.domain;

import com.backend.global.Audit.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken extends Auditable {
    @Id
    @Column(name = "rt_key")
    // member id 값이 들어감
    private Long id;

    @Column(name = "rt_value")
    // refresh token (String)
    private String value;

    @Builder
    public RefreshToken(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}
