package com.backend.domain.question.domain;

import com.backend.domain.answer.domain.Answer;
import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.dto.request.QuestionCreate;
import com.backend.domain.question.dto.request.QuestionUpdate;
import com.backend.domain.vote.domain.QuestionDownVote;
import com.backend.domain.vote.domain.QuestionUpVote;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();


    /**
     * Vote 관련 변수 추가
     * voteState는 현재 로그인한 멤버가 해당질문에 vote했는지 안했는지 여부 표시
     */

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionUpVote> upVotes;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionDownVote> downVotes;

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
