package com.ieltsdemo.dto;

import java.util.List;

public class QuestionDTO {
    String id;
    String technicalDetails;
    private int num; // Номер вопроса
    private String text; // Текст вопроса
    private String correctAnswer; // Правильный ответ
    private String tip; // Подсказка о номере абзаца
    private List<String> options; // Варианты ответа (для закрытых вопросов)
    private String type;


    public QuestionDTO(String id, String technicalDetails, int num, String text, List<String> options, String tip, String correctAnswer, String type) {
        this.id = id;
        this.technicalDetails=technicalDetails;
        this.num = num;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.tip = tip;
        this.options = options;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(String technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
