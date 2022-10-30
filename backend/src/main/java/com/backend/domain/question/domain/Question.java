package com.backend.domain.question.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.global.Audit.Auditable;
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
public class Question extends Auditable {
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

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<QuestionComment> questionComments = new ArrayList<>();


    @Column(name = "isAnswered", nullable = false)
    private Boolean isAnswered;

    @Builder
    public Question(String title, Long view, String content, Member member, Boolean isAnswered) {
        this.title = title;
        this.view = view;
        this.content = content;
        this.member = member;
        this.isAnswered = false;
    }

    public static Question createQuestion(QuestionCreate questionCreate, Member member, List<QuestionTag> questionTags){
        Question question = Question.builder()
                .member(member)
                .view(0L)
                .content(questionCreate.getContent())
                .title(questionCreate.getTitle())
                .build();

        questionTags.forEach(questionTag -> addQuestionTag(question, questionTag));
        
        return question;
    }
    public void updateQuestion(QuestionUpdate questionUpdate,List<QuestionTag> questionTags){
        String changedTitle = questionUpdate.getTitle();
        String changedContent = questionUpdate.getContent();
        this.questionTags.clear();

        questionTags.forEach(questionTag -> addQuestionTag(this, questionTag));

        this.title= changedTitle==null ? this.title : changedTitle;
        this.content = changedContent==null ? this.content : changedContent;

    }

    private static void addQuestionTag(Question question, QuestionTag questionTag) {
        questionTag.setQuestion(question);
        question.getQuestionTags().add(questionTag);
    }

  public void hit(){
        this.view=this.view+1;
    }
}
