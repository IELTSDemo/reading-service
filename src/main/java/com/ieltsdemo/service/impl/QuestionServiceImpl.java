package com.ieltsdemo.service.impl;

import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TextRepository textRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, TextRepository textRepository) {
        this.questionRepository = questionRepository;
        this.textRepository = textRepository;
    }


    @Override
    public List<Question> getQuestionsByTextId(String textId) {
        return questionRepository.findByRelatedTextId(textId);
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
}
