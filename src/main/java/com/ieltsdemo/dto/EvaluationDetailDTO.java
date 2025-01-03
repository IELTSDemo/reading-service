package com.ieltsdemo.dto;

import lombok.Data;

@Data
public class EvaluationDetailDTO {
    private int questionNum;
    private String questionText;
    private String userAnswer;
    private boolean isCorrect;
    private String correctAnswer;

    public EvaluationDetailDTO(int questionNum, String questionText, String userAnswer, boolean isCorrect, String correctAnswer) {
        this.questionNum = questionNum;
        this.questionText = questionText;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
        this.correctAnswer = correctAnswer;
    }
}
