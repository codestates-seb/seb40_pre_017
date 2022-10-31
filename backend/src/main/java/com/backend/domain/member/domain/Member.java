package com.backend.domain.member.domain;

import com.backend.domain.answer.domain.Answer;
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
    private String profileImage = "https://imgur.com/gallery/cfvvtV4";

    @Column(name = "reputation", nullable = false)
    private Long reputation;


    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, String username, String profileImage, Long reputation, List<Question> questions, List<Answer> answers, Authority authority) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.reputation = 0L;
        this.authority = Authority.ROLE_USER;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

}
