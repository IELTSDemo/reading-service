package com.ieltsdemo.model;

import lombok.Data;


public class UserAnswer {
    private String questionId; // ID вопроса, на который отвечает пользователь
    private String answer;     // Ответ пользователя (текст или выбранный вариант)

    public UserAnswer(String questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
