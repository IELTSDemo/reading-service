package com.ieltsdemo.service;

import com.ieltsdemo.dto.EvaluationResult;
import com.ieltsdemo.model.UserAnswer;
import com.ieltsdemo.model.question.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByTextId(String textId);
    Question createQuestion(Question question);
    EvaluationResult evaluateAnswers(List<UserAnswer> userAnswers);
}
