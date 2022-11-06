package com.backend.domain.question.controller;

import com.backend.domain.TestUserDetailService;
import com.backend.domain.member.domain.Member;
import com.backend.domain.member.service.AuthMember;
import com.backend.domain.question.dto.request.UploadImageRequest;
import com.backend.domain.question.service.AwsS3Service;
import com.backend.domain.question.service.ImageUploadService;
import com.backend.domain.question.service.QuestionSearchService;
import com.backend.domain.question.service.QuestionService;
import com.backend.global.repository.MemberRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    AwsS3Service awsS3Service;


    @Test
    void saveImage() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile(
                "attachments","Image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes());

        given(awsS3Service.StoreImage(Mockito.any(MultipartFile.class)))
                .willReturn( "screen-shots/HomePage-attachment.png");


        mockMvc.perform(
                multipart("/questions/uploadImage")
                        .file(firstFile)

        ).andExpect(status().isOk());

    }

}