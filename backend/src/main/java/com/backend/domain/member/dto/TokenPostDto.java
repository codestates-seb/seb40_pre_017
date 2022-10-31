package com.backend.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenPostDto {
    private String accessToken;
    private String refreshToken;

}
