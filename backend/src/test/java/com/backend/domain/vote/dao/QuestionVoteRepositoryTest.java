package com.backend.domain.vote.dao;

import com.backend.domain.comment.domain.QuestionComment;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.question.repository.QuestionRepositoryCustom;
import com.backend.domain.question.repository.QuestionRepositoryImpl;
import com.backend.domain.vote.domain.QuestionDownVote;
import com.backend.domain.vote.domain.QuestionUpVote;
import com.backend.global.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionVoteRepositoryTest {

    @Autowired
    private QuestionUpVoteRepository questionUpVoteRepository;

    @Autowired
    private QuestionDownVoteRepository questionDownVoteRepository;


    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private QuestionRepository questionRepository;

    @MockBean
    private QuestionRepositoryImpl questionRepositoryImpl;

    @Test
    void up() {

        //given
        Question question = Question.builder()
                .title("가나다")
                .view(0L)
                .content("답변 생성 비즈니스로직 테스트용 질문입니다.")
                .build();
        Question savedQuestion = questionRepository.save(question);



        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();
        Member savedMember = memberRepository.save(member);


        //when
         questionUpVoteRepository.up(savedQuestion.getId(), savedMember.getId());

        //then
        assertNull(getQuestionUpVote());
    }



    @Test
    void undoUp() {

        //given
        Question question = Question.builder()
                .title("가나다")
                .view(0L)
                .content("답변 생성 비즈니스로직 테스트용 질문입니다.")
                .build();
        Question savedQuestion = questionRepository.save(question);



        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();
        Member savedMember = memberRepository.save(member);

        //when
        questionUpVoteRepository.up(savedQuestion.getId(), savedMember.getId());
        int result = questionUpVoteRepository.undoUp(savedQuestion.getId(), savedMember.getId());

        //then
        assertEquals(1,result);

    }

    @Test
    void down() {

        //given
        Question question = Question.builder()
                .title("가나다")
                .view(0L)
                .content("답변 생성 비즈니스로직 테스트용 질문입니다.")
                .build();
        Question savedQuestion = questionRepository.save(question);



        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();
        Member savedMember = memberRepository.save(member);


        //when
        questionDownVoteRepository.down(savedQuestion.getId(), savedMember.getId());

        //then
        assertNotNull(getQuestionDownVote());
    }



    @Test
    void undoDown() {

        //given
        Question question = Question.builder()
                .title("가나다")
                .view(0L)
                .content("답변 생성 비즈니스로직 테스트용 질문입니다.")
                .build();
        Question savedQuestion = questionRepository.save(question);



        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();
        Member savedMember = memberRepository.save(member);


        //when
        questionDownVoteRepository.down(savedQuestion.getId(), savedMember.getId());
        int result = questionDownVoteRepository.undoDown(savedQuestion.getId(), savedMember.getId());

        //then
        assertEquals(1,result);
    }
    private QuestionUpVote getQuestionUpVote() {
        return questionUpVoteRepository.findById(1L).get();
    }
    private QuestionDownVote getQuestionDownVote() {
        return questionDownVoteRepository.findById(1L).get();
    }
}