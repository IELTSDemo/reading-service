package com.ieltsdemo.dto.client;

import lombok.Data;

@Data
public class EvaluationDetailDTO {
    private int questionNum;          // Номер вопроса
    private String questionText;      // Текст вопроса
    private String userAnswer;        // Ответ пользователя
    private boolean correct;          // Был ли ответ правильным
    private String correctAnswer;     // Правильный ответ
    private String explanation;       // Пояснение к правильному ответу

    public EvaluationDetailDTO(int questionNum, String questionText, String userAnswer, boolean correct, String correctAnswer, String explanation) {
        this.questionNum = questionNum;
        this.questionText = questionText;
        this.userAnswer = userAnswer;
        this.correct = correct;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }
}
