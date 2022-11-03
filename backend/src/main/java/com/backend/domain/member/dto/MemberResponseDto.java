package com.backend.domain.member.dto;

import com.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String email;
    private String username;
    private String profileImage;
    private Long reputation;

    @Builder
    public MemberResponseDto(Long id) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profileImage = profileImage;
        this.reputation = reputation;
    }


    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .build();
    }


    public Long getId() {
        return id;
    }
}
