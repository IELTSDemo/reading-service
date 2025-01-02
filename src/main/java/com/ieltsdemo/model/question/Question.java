package com.ieltsdemo.model.question;

import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;


@Data
public abstract class Question {
    @Id
    @UuidGenerator
    private String id;
    private int num;
    private String text;
    private String correctAnswer;
    private String relatedTextId; // ID связанного текста
}
