package com.ieltsdemo.controller;

import com.ieltsdemo.dto.client.EvaluationResultDTO;
import com.ieltsdemo.dto.server.EvaluationRequestDTO;
import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.service.EvaluationService;
import com.ieltsdemo.service.ResultService;
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
    private final ResultService resultService;

    public EvaluationController(EvaluationService evaluationService, ResultService resultService) {
        this.evaluationService = evaluationService;
        this.resultService = resultService;
    }

    @PostMapping(value = "/submit", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EvaluationResultDTO> evaluateAnswers(@RequestBody EvaluationRequestDTO request) {
        // Извлечение данных из запроса
        List<AnswerSubmissionDTO> answers = request.getAnswers();
        Result result = new Result();
        result.setTestId(request.getResult().getTestId());
        result.setTextId(request.getResult().getTextId());
        result.setSectionNum(request.getResult().getSectionType());
        result.setCorrectAnswers(request.getResult().getCorrectAnswers());
        result.setTestType(request.getResult().getExamType());
        result.setDeleted(false);
        result.setEmail(request.getResult().getEmail());

        // Оценка ответов и сохранение результата
        EvaluationResultDTO evaluationResult = evaluationService.evaluateAnswers(answers, result);

        return ResponseEntity.ok(evaluationResult);
    }
}
