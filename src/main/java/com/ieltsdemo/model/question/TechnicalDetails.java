package com.ieltsdemo.model.question;

public class TechnicalDetails {
    private String instructions; // Инструкция для вопросов

    public TechnicalDetails(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
