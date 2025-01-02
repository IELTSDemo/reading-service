package com.ieltsdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    private String text;
    private List<String> options; // Варианты ответа
    private String correctAnswer; // Правильный ответ
    private String explanation;
    private String relatedTextId; // ID связанного текста (из коллекции Text)
}
