package com.ieltsdemo.controller;

import com.ieltsdemo.dto.client.EvaluationResultDTO;
import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/submit")
    public ResponseEntity<EvaluationResultDTO> evaluateAnswers(@RequestBody List<AnswerSubmissionDTO> answers) {
        EvaluationResultDTO evaluationResult = evaluationService.evaluateAnswers(answers);
        return ResponseEntity.ok(evaluationResult);
    }
}
