package com.backend.domain.member.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.dto.MemberResponseDto;
import com.backend.domain.question.domain.Question;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "reputation", nullable = false)
    private Long reputation;


    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(Long id, String email, String password, String username, String profileImage, Long reputation, List<Question> questions, List<Answer> answers, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
        this.reputation = reputation;
        this.questions = questions;
        this.answers = answers;
        this.authority = authority;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    public MemberResponseDto toResponseDto() {
        return MemberResponseDto.builder()
                .id(id)
                .build();
    }

    public MemberResponseDto toResponseDto (Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
//                .email(member.getEmail())
//                .username(member.getUsername())
//                .profileImage(member.getProfileImage())
//                .reputation(member.getReputation())
                .build();
    }
}
