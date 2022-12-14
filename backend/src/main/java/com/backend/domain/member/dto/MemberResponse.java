package com.backend.domain.member.dto;

import com.backend.domain.member.domain.Member;
import com.backend.global.util.Constant;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long reputation;
    private Long userId;
    private String profileImage;
    private String username;
    private String link;

    @Builder
    public MemberResponse(Long reputation, Long userId, String profileImage, String username, String link) {
        this.reputation = reputation;
        this.userId = userId;
        this.profileImage = profileImage;
        this.username = username;
        this.link = link;
    }

    public static MemberResponse toResponse(Member member){
        return MemberResponse.builder()
                .profileImage(member.getProfileImage())
                .reputation(member.getReputation())
                .userId(member.getId())
                .username(member.getUsername())
                .link(memberLink(member))
                .build();
    }

    private static String memberLink(Member member) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("member/").path(member.getId().toString())
                .replaceQuery(null)
                .toUriString();

    }

}
