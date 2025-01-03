package com.ieltsdemo.dto;

public class TestDTO {
    private String name; // Название теста, например, "IELTS Test 1"

    public TestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
