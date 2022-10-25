package com.backend.domain.member.dto;

import com.backend.domain.member.domain.Member;
import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private String profileImage;
    private Long reputation;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.profileImage = member.getProfileImage();
        this.reputation = member.getReputation();
    }

//    @Getter
//    @AllArgsConstructor
//    public static class Post {
//        private String email;
//        private String password;
//        private String username;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    public static class Patch {
//        private String username;
//        private String password;
//        private String profileImage;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    @Builder
//    public static class Response {
//        private Long id;
//        private String email;
//        private String password;
//        private String username;
//        private String profileImage;
//        private Long reputation;
//    }

}
