package com.ieltsdemo.dto;

import java.util.List;

public class QuestionDTO {
    private String id;
    private String technicalDetails;
    private int num; // Номер вопроса
    private String text; // Текст вопроса
    private List<String> options; // Варианты ответов (для закрытых вопросов)
    private String tip; // Подсказка

    public QuestionDTO(String id, String technicalDetails, int num, String text, List<String> options, String tip) {
        this.id = id;
        this.technicalDetails = technicalDetails;
        this.num = num;
        this.text = text;
        this.options = options;
        this.tip = tip;
    }
}
