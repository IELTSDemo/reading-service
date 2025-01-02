package com.ieltsdemo.controller;

import com.ieltsdemo.dto.EvaluationResult;
import com.ieltsdemo.model.UserAnswer;
import com.ieltsdemo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evaluate")
@RequiredArgsConstructor
public class EvaluationController {

    private final QuestionService questionService;

    @PostMapping
    public EvaluationResult evaluateAnswers(@RequestBody List<UserAnswer> userAnswers) {
        return questionService.evaluateAnswers(userAnswers);
    }
}
