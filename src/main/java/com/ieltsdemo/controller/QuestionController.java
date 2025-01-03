package com.ieltsdemo.controller;

import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{textId}")
    public List<Question> getQuestionsByTextId(@PathVariable String textId) {
        return questionService.getQuestionsByTextId(textId);
    }
}
