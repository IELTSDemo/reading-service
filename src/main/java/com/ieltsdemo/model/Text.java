package com.ieltsdemo.model;

import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "texts")
public class Text {
    @Id
    @UuidGenerator
    private String id;
    private String title; // Название текста
    private String content; // Содержание текста
    private List<Image> images; // Изображения
    private String testName; // Название теста (например, "IELTS Test 1")
    private SectionType section; // Название теста (например, "Section 1")
    private String textNumber; // Название теста (например, "Text 1")
    private ExamType examType; // Тип экзамена (General или Academic)
}
