package com.backend.domain.member.controller;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
public class LoginDto {

    @Email
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;


    @Builder
    public LoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
