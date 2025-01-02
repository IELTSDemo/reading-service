package com.ieltsdemo.controller;

import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }
}
