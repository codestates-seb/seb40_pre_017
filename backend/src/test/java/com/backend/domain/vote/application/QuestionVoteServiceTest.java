package com.backend.domain.vote.application;

import com.backend.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionVoteServiceTest {

    @Test
    void up() throws Exception {

        //given

        Member member = Member.builder()
                .password("123456")
                .username("가나다")
                .email("abc@gmail.com")
                .build();

        //when
        member.questionUpVoted();

        //then
        assertEquals(5, member.getReputation());

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
        member.undoQuestionUpVoted();


        assertEquals(-5, member.getReputation());

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
        member.questionDownVoted();


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
        member.undoQuestionDownVoted();
        //then
        assertEquals(+2, member.getReputation());

    }
}