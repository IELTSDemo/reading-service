package com.ieltsdemo.dto;

import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;

import java.util.List;

public class UploadTextDTO {
    private String title; // Название текста
    private String content; // Содержание текста
    private String testName; // Название теста (например, "IELTS Test 1")
    private SectionType section; // Секция (например, "Section 1")
    private String textNumber; // Номер текста (например, "Text 1")
    private ExamType examType; // Тип экзамена (General или Academic)
    private List<QuestionDTO> questions; // Список вопросов

    public UploadTextDTO(String title, String content, String testName, SectionType section, String textNumber, ExamType examType, List<QuestionDTO> questions) {
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

    public SectionType getSection() {
        return section;
    }

    public void setSection(SectionType section) {
        this.section = section;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
