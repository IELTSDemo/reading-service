package com.ieltsdemo.controller;

import com.ieltsdemo.dto.client.TextDTO;
import com.ieltsdemo.dto.client.TextAndQuestionsDTO;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.util.SectionType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/texts")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    /**
     * Получение списка текстов по ID теста и секции
     *
     * @param testId  ID теста
     * @param section Секция текста
     * @return Список текстов
     */
    @GetMapping
    public List<TextDTO> getTextsByTestIdAndSection(@RequestParam String testId, @RequestParam SectionType section) {
        return textService.findTextByTestIdAndSection(testId, section);
    }

    /**
     * Получение текста и связанных вопросов
     *
     * @param textId ID текста
     * @return DTO, содержащий текст и вопросы
     */
    @GetMapping("/{textId}")
    public TextAndQuestionsDTO getTextAndQuestions(
            @PathVariable String textId,
            @RequestParam String email) {
        return textService.getTextAndQuestions(textId, email);
    }

}
