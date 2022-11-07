package com.backend.domain.question.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class UploadImageRequest {
	private MultipartFile img;

	public UploadImageRequest(MultipartFile img) {
		this.img = img;
	}
}
