package com.ieltsdemo.dto;

public class TextDTO {
    private String id;
    private String title; // Название текста
    private String textNumber; // Номер текста, например, "Text 1"

    public TextDTO(String id, String title, String textNumber) {
        this.id = id;
        this.title = title;
        this.textNumber = textNumber;
    }
}
