package com.ieltsdemo.service;

import com.ieltsdemo.dto.EvaluationDetail;
import com.ieltsdemo.dto.EvaluationResult;
import com.ieltsdemo.model.UserAnswer;
import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestionsByTextId(String textId) {
        return questionRepository.findByRelatedTextId(textId);
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public EvaluationResult evaluateAnswers(List<UserAnswer> userAnswers) {
        int score = 0;
        List<EvaluationDetail> details = new ArrayList<>();

        for (UserAnswer userAnswer : userAnswers) {
            Question question = questionRepository.findById(userAnswer.getQuestionId()).orElse(null);
            if (question != null) {
                boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(userAnswer.getAnswer());
                if (isCorrect) {
                    score++;
                }
                details.add(new EvaluationDetail(question.getNum(), question.getText(), userAnswer.getAnswer(), isCorrect, question.getCorrectAnswer()));
            }
        }
        return new EvaluationResult(score, details);
    }
}
