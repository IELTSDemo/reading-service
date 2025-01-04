package com.ieltsdemo.dto.server;

import com.ieltsdemo.util.QuestionType;
import com.ieltsdemo.util.SectionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UploadTextWithQuestionsDTO {
    private String testId; // ID теста, к которому относится текст
    private SectionType section; // Секция текста ("SECTION_ONE", "SECTION_TWO", "SECTION_THREE")
    private int textNumber; // Номер текста
    private String introduction; // Введение
    private String title; // Название текста
    private String content; // Содержание текста
    private List<QuestionDTO> questions; // Вопросы, связанные с текстом

    @Data
    @AllArgsConstructor
    public static class QuestionDTO {
        private int num; // Номер вопроса
        private String text; // Текст вопроса
        private String correctAnswer; // Правильный ответ
        private int tip; // Подсказка
        private QuestionType type; // Тип вопроса (OPEN, CLOSED)
        private List<String> options; // Варианты ответа (только для CLOSED)
        private String technicalDetails; // Технические детали
        private String explanation; // Технические детали
    }
}
