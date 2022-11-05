package com.backend.domain.question.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.backend.domain.question.dto.request.UploadImageRequest;
import com.backend.domain.question.service.AwsS3Service;
import com.backend.domain.question.service.ImageUploadService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImageUploadController {

	private final ImageUploadService awsS3Service;

	public ImageUploadController(AwsS3Service awsS3Service) {
		this.awsS3Service = awsS3Service;
	}

	@PostMapping("/questions/uploadImage")
	public String saveImage(UploadImageRequest uploadImageRequest){
		log.info("upload Image = {} ", uploadImageRequest.getImg());

		return awsS3Service.StoreImage(uploadImageRequest.getImg());

	}

}
