package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.QuestionDTO;
import com.ieltsdemo.dto.client.TextAndQuestionsDTO;
import com.ieltsdemo.dto.client.TextDTO;
import com.ieltsdemo.model.question.ClosedQuestion;
import com.ieltsdemo.model.text.Text;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.util.SectionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TextServiceImpl implements TextService {

    private final TextRepository textRepository;

    public TextServiceImpl(TextRepository textRepository) {
        this.textRepository = textRepository;
    }


    @Override
    public List<TextDTO> findTextByTestIdAndSection(String testId, SectionType section) {
        return textRepository.findTextByTestIdAndSection(testId, section).stream()
                .map(text -> new TextDTO(
                        text.getId(),
                        text.getTitle()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public TextAndQuestionsDTO getTextAndQuestions(String textId) {
        // Получение текста
        Text text = textRepository.findById(textId)
                .orElseThrow(() -> new RuntimeException("Text not found"));

        // Получение вопросов, связанных с текстом
        List<QuestionDTO> questions = text.getQuestions()
                .stream()
                .map(question -> {
                    // Если вопрос закрытого типа, добавить опции
                    if (question instanceof ClosedQuestion closedQuestion) {
                        return new QuestionDTO(
                                question.getId(),
                                question.getTechnicalDetails(),
                                question.getNum(),
                                question.getQuestion(),
                                question.getCorrectAnswer(),
                                question.getTipParagraph(), // Опции для закрытого вопроса
                                closedQuestion.getOptions(),
                                question.getType()
                        );
                    } else { // Для открытого типа
                        return new QuestionDTO(
                                question.getId(),
                                question.getTechnicalDetails(),
                                question.getNum(),
                                question.getQuestion(),
                                question.getCorrectAnswer(),
                                question.getTipParagraph(),
                                question.getType()
                        );
                    }
                })
                .collect(Collectors.toList());

        // Возврат DTO с текстом и вопросами
        return new TextAndQuestionsDTO(
                text.getTitle(),
                text.getIntroduction(), // Описание текста
                text.getContent(),      // Сам текст
                questions               // Список вопросов
        );
    }
}
