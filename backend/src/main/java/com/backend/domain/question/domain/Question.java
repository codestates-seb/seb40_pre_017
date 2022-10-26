package com.backend.domain.question.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.dto.QuestionCreateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "view", nullable = false)
    private Long view;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @Builder
    public Question(String title, Long view, String content, Member member) {
        this.title = title;
        this.view = view;
        this.content = content;
        this.member = member;

    }

    public static Question createQuestion(QuestionCreateDto questionCreateDto, Member member, List<QuestionTag> questionTags){
        Question question = Question.builder()
                .member(member)
                .view(0L)
                .content(questionCreateDto.getContent())
                .title(questionCreateDto.getTitle())
                .build();

        for (QuestionTag questionTag : questionTags) {
            questionTag.setQuestion(question);
            question.getQuestionTags().add(questionTag);

        }
//        question.getQuestionTags().addAll(questionTags);

        return question;
    }
}
