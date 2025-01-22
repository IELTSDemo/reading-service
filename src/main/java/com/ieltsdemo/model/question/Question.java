package com.ieltsdemo.model.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ieltsdemo.util.QuestionType;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type" // Поле для определения типа вопроса
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OpenQuestion.class, name = "OPEN"),
        @JsonSubTypes.Type(value = ClosedQuestion.class, name = "CLOSED")
})

@Data
public abstract class Question {
    @Id
    @UuidGenerator
    private String id;
    private String technicalDetails;
    private String num; // Номер вопроса
    private String question; // Текст вопроса
    private String correctAnswer; // Правильный ответ
    private String relatedTextId; // ID текста, к которому относится вопрос
    private int tipParagraph; // Подсказка о номере абзаца
    private String explanation;
    private QuestionType type; // Подсказка о номере абзаца

}
