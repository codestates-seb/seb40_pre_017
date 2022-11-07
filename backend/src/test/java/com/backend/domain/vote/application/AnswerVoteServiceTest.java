package com.backend.domain.vote.application;

import com.backend.domain.answer.application.AnswerService;
import com.backend.domain.answer.dao.AnswerRepository;
import com.backend.domain.answer.domain.Answer;
import com.backend.domain.answer.dto.AnswerCreate;
import com.backend.domain.answer.dto.AnswerUpdate;
import com.backend.domain.member.domain.Member;
import com.backend.domain.question.domain.Question;
import com.backend.domain.vote.dao.AnswerDownVoteRepository;
import com.backend.domain.vote.dao.AnswerUpVoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerVoteServiceTest {

    @Test
    void up() throws Exception {

        //given

        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();

        //when
        member.answerUpVoted();

        //then
        assertEquals(10, member.getReputation());

    }

    @Test
    void undoUp() throws Exception {

        //given

        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();
        //when
        member.undoAnswerUpVoted();


        assertEquals(-10, member.getReputation());

    }

    @Test
    void down() throws Exception {

        //given
        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();

        //when
        member.answerDownVoted();


        //then
        assertEquals(-2, member.getReputation());

    }

    @Test
    void undoDown() throws Exception {

        //given
        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();

        //when
        member.undoAnswerDownVoted();
        //then
        assertEquals(+2, member.getReputation());

    }
}