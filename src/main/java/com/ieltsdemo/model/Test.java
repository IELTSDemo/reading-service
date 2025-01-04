package com.ieltsdemo.model;

import com.ieltsdemo.model.text.Text;
import com.ieltsdemo.util.ExamType;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tests")
public class Test {
    @Id
    @UuidGenerator
    private String id; // Уникальный идентификатор теста
    private String name; // Название теста (IELTS Test 1, IELTS Test 2)
    private ExamType examType; // Тип экзамена (GENERAL или ACADEMIC)
    private List<Text> texts; // Список текстов для этого теста
}
