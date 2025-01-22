package com.ieltsdemo.dto.client;

import com.ieltsdemo.util.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    public QuestionDTO(String id, String technicalDetails, String num, String text, String correctAnswer, int tip, List<String> options, QuestionType type) {
        this.id = id;
        this.technicalDetails = technicalDetails;
        this.num = num;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.tip = tip;
        this.options = options;
        this.type = type;
    }

    String id;
    String technicalDetails;
    private String num; // Номер вопроса
    private String text; // Текст вопроса
    private String correctAnswer; // Правильный ответ
    private int tip; // Подсказка о номере абзаца
    private List<String> options; // Варианты ответа (для закрытых вопросов)
    private QuestionType type;
    private boolean hasAnswered;

    public QuestionDTO(String id, String technicalDetails, String num, String text, String correctAnswer, int tip, QuestionType type, boolean hasAnswered) {
        this.id = id;
        this.technicalDetails = technicalDetails;
        this.num = num;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.tip = tip;
        this.type = type;
        this.hasAnswered = hasAnswered;
    }

    public QuestionDTO(String id, String technicalDetails, String num, String text, String correctAnswer, int tip, List<String> options, QuestionType type, boolean hasAnswered) {
        this.id = id;
        this.technicalDetails = technicalDetails;
        this.num = num;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.tip = tip;
        this.options = options;
        this.type = type;
        this.hasAnswered = hasAnswered;
    }

    public QuestionDTO(String id, String technicalDetails, String num, String question, String correctAnswer, int tipParagraph, QuestionType type) {
        this.id = id;
        this.technicalDetails = technicalDetails;
        this.num = num;
        this.text = question;
        this.correctAnswer = correctAnswer;
        this.tip = tipParagraph;
        this.type = type;
    }
}
