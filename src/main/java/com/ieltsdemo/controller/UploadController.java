package com.ieltsdemo.controller;

import com.ieltsdemo.dto.UploadTextDTO;
import com.ieltsdemo.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<String> uploadTextAndQuestions(@RequestBody UploadTextDTO uploadTextDTO) {
        uploadService.uploadTextAndQuestions(uploadTextDTO);
        return ResponseEntity.ok("Text and questions uploaded successfully.");
    }
}

