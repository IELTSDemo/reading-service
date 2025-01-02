package com.ieltsdemo.model.question;

import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "question_sets")
public class QuestionSet {
    @Id
    @UuidGenerator
    private String id; // Уникальный ID для набора вопросов
    private TechnicalDetails technicalDetails; // Техническая информация
    private List<Question> questions; // Список вопросов (ClosedQuestion, OpenQuestion)
}
