package com.backend.domain.question.domain;

import com.backend.domain.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionTag {
    // 질문 A  - > TAG A TAG B
    // TAG A -> 질문 A, 질문 B

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public QuestionTag(Tag tag) {
        this.tag = tag;
    }

    public void setQuestion(Question question){
        this.question=question;
    }

    public static QuestionTag createQuestionTag(Tag tag){
        QuestionTag questionTag = QuestionTag.builder()
                .tag(tag)
                .build();
        return questionTag;
    }

}
