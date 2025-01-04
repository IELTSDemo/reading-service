package com.ieltsdemo.util;

import lombok.Getter;

@Getter
public enum QuestionType {
    OPEN("OPEN"),   // Значение для OPEN
    CLOSED("CLOSED"); // Значение для CLOSED

    private final String value; // Поле для хранения значения

    // Конструктор для задания значения
    QuestionType(String value) {
        this.value = value;
    }

    // Метод для получения значения
}
