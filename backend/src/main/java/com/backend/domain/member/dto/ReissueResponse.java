package com.backend.domain.member.dto;

import com.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueResponse {

    private String email;
    private String username;
    private String imageUrl;

    @Builder
    public ReissueResponse(String email, String imageUrl, String username) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public static ReissueResponse toResponse(Member member) {
        return ReissueResponse.builder()
                .email(member.getEmail())
                .imageUrl(member.getProfileImage())
                .username(member.getUsername())
                .build();
    }

}
