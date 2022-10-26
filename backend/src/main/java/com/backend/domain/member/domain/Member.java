package com.backend.domain.member.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.question.domain.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
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

    @Builder
    public Member(String email, String password, String username, String profileImage, Long reputation) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
        this.reputation = reputation;
    }
}
