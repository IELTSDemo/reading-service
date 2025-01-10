package com.ieltsdemo.model;

import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "test_id", nullable = false)
    private String testId;

    @Column(name = "text_id", nullable = false)
    private String textId;

    @Column(name = "section_num", nullable = false)
    @Enumerated(EnumType.STRING)
    private SectionType sectionNum;

    @Column(name = "correct_answers", nullable = false)
    private int correctAnswers;

    @Column(name = "test_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExamType testType;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @PreRemove
    public void markAsDeleted() {
        this.deleted = true;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public SectionType getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(SectionType sectionNum) {
        this.sectionNum = sectionNum;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public ExamType getTestType() {
        return testType;
    }

    public void setTestType(ExamType testType) {
        this.testType = testType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
