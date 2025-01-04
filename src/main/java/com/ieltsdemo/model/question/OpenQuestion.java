package com.ieltsdemo.model.question;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OpenQuestion extends Question {
    // У открытого вопроса дополнительных полей нет
}
