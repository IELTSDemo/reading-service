package com.ieltsdemo.controller;

import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.dto.server.UploadTextWithQuestionsDTO;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/upload")
@AllArgsConstructor
public class UploadController {

    private final UploadService uploadService;
    private final TestService testService;
    private final TextService textService;

    @PostMapping(value = "/text-with-questions", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> uploadTextWithQuestions(@RequestBody UploadTextWithQuestionsDTO dto) {
        try {
            uploadService.uploadTextWithQuestions(dto);
            return ResponseEntity.ok("Text with questions uploaded successfully");
        } catch (IllegalArgumentException e) {
            // Если title не уникален, или любая другая логика
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping(value = "/text-with-questions/{textId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> updateTextWithQuestions(@PathVariable String textId,
                                                          @RequestBody UploadTextWithQuestionsDTO dto) {
        uploadService.updateTextWithQuestions(textId, dto);
        return ResponseEntity.ok("Text with questions updated successfully");
    }

    @PostMapping(value = "/create-test", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> createTest(@RequestBody CreateTestDTO createTestDTO) {
        try {
            testService.createTest(createTestDTO);
            return ResponseEntity.ok("Test created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-test/{id}")  // Используем DELETE для удаления
    public ResponseEntity<String> deleteTest(@PathVariable String id) {
        testService.deleteTestById(id);
        return ResponseEntity.ok("Test deleted successfully");
    }

    @DeleteMapping("/delete-text/{id}")  // Используем DELETE для удаления
    public ResponseEntity<String> deleteText(@PathVariable String id) {
        textService.deleteTextByTextId(id);
        return ResponseEntity.ok("Text deleted successfully"); // Исправили на "Text"
    }

}
