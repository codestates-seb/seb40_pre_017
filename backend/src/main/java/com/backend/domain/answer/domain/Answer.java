package com.backend.domain.answer.domain;

import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.comment.domain.AnswerComment;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.AnswerUpVote;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;


    @Column(name = "is_accepted",nullable = false)
    private Boolean isAccepted;


    @OneToMany(mappedBy = "answer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerUpVote> upVotes;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerDownVote> downVotes;


    @Formula("(select\n" +
            "            count(answer_up_vote.answer_id)          \n" +
            "        from\n" +
            "            answer_up_vote          \n" +
            "        where\n" +
            "             answer_up_vote.answer_id = answer_id)" +
            "-(select\n" +
            "            count(answer_down_vote.answer_id)          \n" +
            "        from\n" +
            "            answer_down_vote          \n" +
            "        where\n" +
            "            answer_id = answer_down_vote.answer_id)")
    private Integer voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "answer",cascade = CascadeType.ALL)
    private List<AnswerComment> answerComments = new ArrayList<>();

    @Builder
    public Answer(String content, Boolean isAccepted, Member member, Question question) {
        this.content = content;
        this.isAccepted = isAccepted;
        this.member = member;
        this.question=question;
    }

    public void patch(AnswerUpdate dto) {

        if(dto.getContent() != null)
            this.content = dto.getContent();
    }

    public void accept() {
        this.isAccepted = true;
    }
    public void unAccept() {
        this.isAccepted = false;
    }

}
