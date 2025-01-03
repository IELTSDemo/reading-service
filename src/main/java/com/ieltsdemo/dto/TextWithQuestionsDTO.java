package com.ieltsdemo.dto;

import java.util.List;

public class TextWithQuestionsDTO {
    private String textContent;
    private List<QuestionDTO> questions;

    public TextWithQuestionsDTO(String textContent, List<QuestionDTO> questions) {
        this.textContent = textContent;
        this.questions = questions;
    }
}
