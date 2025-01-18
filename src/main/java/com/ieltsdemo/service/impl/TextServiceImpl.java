package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.QuestionDTO;
import com.ieltsdemo.dto.client.TextAndQuestionsDTO;
import com.ieltsdemo.dto.client.TextDTO;
import com.ieltsdemo.dto.server.ResultDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.question.ClosedQuestion;
import com.ieltsdemo.model.text.Text;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.repository.ResultRepository;
import com.ieltsdemo.repository.TestRepository;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TextServiceImpl implements TextService {

    private final TextRepository textRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final ResultRepository resultRepository;


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
    public TextAndQuestionsDTO getTextAndQuestions(String textId, String email) {
        //Отвечал ли юзер?
        boolean hasAnswered = resultRepository.existsByTextIdAndEmailAndDeleted(textId,email,false);
        // Получение текста
        Text text = textRepository.findById(textId)
                .orElseThrow(() -> new RuntimeException("Text not found"));

        // Получение вопросов, связанных с текстом
        List<QuestionDTO> questions = questionRepository.findByRelatedTextId(textId)
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
                                question.getType(),
                                hasAnswered
                        );
                    } else { // Для открытого типа
                        return new QuestionDTO(
                                question.getId(),
                                question.getTechnicalDetails(),
                                question.getNum(),
                                question.getQuestion(),
                                question.getCorrectAnswer(),
                                question.getTipParagraph(),
                                question.getType(),
                                hasAnswered
                        );
                    }
                })
                .collect(Collectors.toList());

        ResultDTO resultDTO = new ResultDTO();
        ExamType examType = testRepository.findTestById(text.getTestId()).get(0).getExamType();
        resultDTO.setTextId(textId);
        resultDTO.setSectionType(text.getSection());
        resultDTO.setTestId(text.getTestId());
        resultDTO.setExamType(examType);

        // Возврат DTO с текстом и вопросами
        return new TextAndQuestionsDTO(
                text.getTitle(),
                text.getIntroduction(), // Описание текста
                text.getContent(),      // Сам текст
                questions,               // Список вопросов
                resultDTO
        );
    }

    @Override
    public void deleteTextByTextId(String testId) {
        textRepository.deleteTextById(testId);
    }
}
