package com.ieltsdemo.controller;

import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.dto.server.UploadTextWithQuestionsDTO;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/upload")
@AllArgsConstructor
public class UploadController {

    private final UploadService uploadService;
    private final TestService testService;

    @PostMapping("/text-with-questions")
    public ResponseEntity<String> uploadTextWithQuestions(@RequestBody UploadTextWithQuestionsDTO dto) {
        uploadService.uploadTextWithQuestions(dto);
        return ResponseEntity.ok("Text with questions uploaded successfully");
    }

    @PostMapping("/create-test")
    public ResponseEntity<String> createTest(@RequestBody CreateTestDTO createTestDTO) {
        testService.createTest(createTestDTO);
        return ResponseEntity.ok("Test created successfully");
    }
}

