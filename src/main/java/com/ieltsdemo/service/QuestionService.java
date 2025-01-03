package com.ieltsdemo.service;

import com.ieltsdemo.model.question.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByTextId(String textId);
    Question createQuestion(Question question);
}
