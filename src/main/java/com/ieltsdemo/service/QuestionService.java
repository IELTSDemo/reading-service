package com.ieltsdemo.service;

import com.ieltsdemo.model.Question;
import com.ieltsdemo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findById(String id) {
        return questionRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        questionRepository.deleteById(id);
    }
}
