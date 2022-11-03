package com.backend.domain.vote.dao;

import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;

import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.question.repository.QuestionRepository;
import com.backend.domain.question.repository.QuestionRepositoryImpl;
import com.backend.domain.vote.domain.AnswerDownVote;
import com.backend.domain.vote.domain.AnswerUpVote;
import com.backend.global.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerVoteRepositoryTest {

    @Autowired
    private AnswerUpVoteRepository answerUpVoteRepository;

    @Autowired
    private AnswerDownVoteRepository answerDownVoteRepository;

    @MockBean
    private QuestionRepositoryImpl questionRepositoryImpl;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AnswerRepository answerRepository;


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

        String content = "답변 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();
        Answer savedAnswer = answerRepository.save(answer);


        //when
        answerUpVoteRepository.up(savedAnswer.getId(), savedMember.getId());

        //then
        assertNotNull(answerUpVoteRepository.findById(1L));
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

        String content = "답변 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();
        Answer savedAnswer = answerRepository.save(answer);


        //when
        answerUpVoteRepository.up(savedAnswer.getId(), savedMember.getId());
        int result = answerUpVoteRepository.undoUp(savedAnswer.getId(), savedMember.getId());

        //then
        assertEquals(1, result);

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

        String content = "답변 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();
        Answer savedAnswer = answerRepository.save(answer);


        //when
        answerDownVoteRepository.down(savedAnswer.getId(), savedMember.getId());

        //then
        assertNotNull(answerDownVoteRepository.findById(1L));
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

        String content = "답변 생성 비즈니스 로직 테스트용 답변생성";

        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .isAccepted(false)
                .build();
        Answer savedAnswer = answerRepository.save(answer);


        //when
        answerDownVoteRepository.down(savedAnswer.getId(), savedMember.getId());
        int result = answerDownVoteRepository.undoDown(savedAnswer.getId(), savedMember.getId());

        //then
        assertEquals(1, result);
    }



}