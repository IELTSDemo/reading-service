package com.ieltsdemo.model.text;

import com.ieltsdemo.model.question.Question;
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
    private String introduction; // Описание, например: "Read the text below and answer Questions 8-14."
    private String title; // Название текста
    private String content; // Содержание текста
    private String testId; // ID теста
    private SectionType section; // Секция текста ("SECTION_ONE", "SECTION_TWO", "SECTION_THREE")
    private int textNumber; // Номер текста (например, 1)
    private List<Question> questions; // Вопросы, связанные с текстом
    private List<Image> images; // Список изображений, связанных с текстом
}
