package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.QuestionDTO;
import com.ieltsdemo.dto.TextDTO;
import com.ieltsdemo.dto.TextWithQuestionsDTO;
import com.ieltsdemo.model.Text;
import com.ieltsdemo.model.question.ClosedQuestion;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.TextService;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TextServiceImpl implements TextService {

    private final TextRepository textRepository;
    private final QuestionRepository questionRepository;

    public TextServiceImpl(TextRepository textRepository, QuestionRepository questionRepository) {
        this.textRepository = textRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<TextDTO> getTextsBySectionAndExamType(SectionType section, ExamType examType) {
        return textRepository.findTextBySectionAndExamType(section, examType).stream()
                .map(text -> new TextDTO(text.getId(), text.getTitle(), text.getTextNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public TextWithQuestionsDTO getTextAndQuestions(String textId) {
        Text text = textRepository.findById(textId).orElseThrow(() -> new RuntimeException("Text not found"));
        List<QuestionDTO> questions = questionRepository.findByRelatedTextId(textId)
                .stream()
                .map(question -> new QuestionDTO(
                        question.getId(),
                        question.getTechnicalDetails(),
                        question.getNum(),
                        question.getText(),
                        question instanceof ClosedQuestion ? ((ClosedQuestion) question).getOptions() : null,
                        question.getTip(),
                        question.getCorrectAnswer(),
                        question.getType()
                ))
                .collect(Collectors.toList());
        return new TextWithQuestionsDTO(text.getContent(), questions);
    }
}
