package com.ieltsdemo.model;

import lombok.Data;

@Data
public class UserAnswer {
    private String questionId; // ID вопроса, на который отвечает пользователь
    private String answer;     // Ответ пользователя (текст или выбранный вариант)
}
