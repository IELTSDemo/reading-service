package com.ieltsdemo.controller;

import com.ieltsdemo.dto.AnswerSubmissionDTO;
import com.ieltsdemo.dto.EvaluationResultDTO;
import com.ieltsdemo.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<EvaluationResultDTO> evaluateAnswers(@RequestBody AnswerSubmissionDTO submissionDTO) {
        EvaluationResultDTO result = evaluationService.evaluateAnswers(submissionDTO);
        return ResponseEntity.ok(result);
    }
}
