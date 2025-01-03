package com.ieltsdemo.dto;

import java.util.List;

public class UploadTextDTO {
    private String title; // Название текста
    private String content; // Содержание текста
    private String testName; // Название теста (например, "IELTS Test 1")
    private String section; // Секция (например, "Section 1")
    private String textNumber; // Номер текста (например, "Text 1")
    private String examType; // Тип экзамена (General или Academic)
    private List<QuestionDTO> questions; // Список вопросов

    public UploadTextDTO(String title, String content, String testName, String section, String textNumber, String examType, List<QuestionDTO> questions) {
        this.title = title;
        this.content = content;
        this.testName = testName;
        this.section = section;
        this.textNumber = textNumber;
        this.examType = examType;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
