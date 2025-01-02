package com.ieltsdemo.model.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
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
@Data
public abstract class Question {
    @Id
    @UuidGenerator
    @JsonIgnore
    private String id;
    private TechnicalDetails technicalDetails;
    private int num;
    private String text;
    private String correctAnswer;
    private String relatedTextId; // ID связанного текста
}
