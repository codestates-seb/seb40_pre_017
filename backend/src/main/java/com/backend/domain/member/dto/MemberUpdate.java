package com.backend.domain.member.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdate {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;


    private String profileImage;

    @Builder
    public MemberUpdate(String password, String username, String profileImage) {
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
    }
}
