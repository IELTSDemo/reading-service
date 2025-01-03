package com.ieltsdemo.controller;

import com.ieltsdemo.dto.TextDTO;
import com.ieltsdemo.dto.TextWithQuestionsDTO;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/texts")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    public List<TextDTO> getTexts(@RequestParam SectionType section, @RequestParam ExamType examType) {
        return textService.getTextsBySectionAndExamType(section, examType);
    }

    @GetMapping("/{textId}")
    public TextWithQuestionsDTO getTextAndQuestions(@PathVariable String textId) {
        return textService.getTextAndQuestions(textId);
    }
}
