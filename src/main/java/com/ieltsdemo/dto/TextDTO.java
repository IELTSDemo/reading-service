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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }
}
