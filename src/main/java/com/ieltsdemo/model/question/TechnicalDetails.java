package com.ieltsdemo.model.question;

import lombok.Data;

@Data
public class TechnicalDetails {
    private String instructions; // Инструкция для вопросов
    private String questionType; // Тип вопроса (CLOSED или OPEN)
}
