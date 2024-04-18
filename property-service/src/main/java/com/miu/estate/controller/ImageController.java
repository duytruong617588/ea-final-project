package com.miu.estate.controller;

import com.miu.estate.dto.response.ImageResponse;
import com.miu.estate.model.Image;
import com.miu.estate.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam String description
    ) {
        ImageResponse response = new ImageResponse();
        try {
            Image image = imageService.saveImage(file, description);
            response.setUrl(image.getUrl());
            response.setDescription(image.getDescription());
            response.setMessage("File uploaded successfully");
            response.setSuccess(true);
        } catch (IOException e) {
            response.setMessage("File upload failed: " + e.getMessage());
            response.setSuccess(false);
        } finally {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
