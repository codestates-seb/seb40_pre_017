package com.backend.domain.question.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



public interface ImageUploadService {


	String StoreImage(MultipartFile img);


}
