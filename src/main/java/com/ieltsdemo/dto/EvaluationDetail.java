package com.ieltsdemo.dto;

import lombok.Data;

@Data
public class EvaluationDetail {
    private int questionNum;
    private String questionText;
    private String userAnswer;
    private boolean isCorrect;

    public EvaluationDetail(int questionNum, String questionText, String userAnswer, boolean isCorrect) {
        this.questionNum = questionNum;
        this.questionText = questionText;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }
}
