package com.ieltsdemo.model.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type" // Поле, по которому определяется тип вопроса
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OpenQuestion.class, name = "OPEN"),
        @JsonSubTypes.Type(value = ClosedQuestion.class, name = "CLOSED")
})
public abstract class Question {
    @Id
    @UuidGenerator
    private String id;
    private TechnicalDetails technicalDetails;
    private int num; // Номер вопроса
    private String text; // Текст вопроса
    private String correctAnswer; // Правильный ответ
    private String relatedTextId; // ID текста, к которому относится вопрос
    private String tip; // Подсказка о номере абзаца

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TechnicalDetails getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(TechnicalDetails technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getRelatedTextId() {
        return relatedTextId;
    }

    public void setRelatedTextId(String relatedTextId) {
        this.relatedTextId = relatedTextId;
    }
}
