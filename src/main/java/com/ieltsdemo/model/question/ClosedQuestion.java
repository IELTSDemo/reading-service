package com.ieltsdemo.model.question;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class ClosedQuestion extends Question {
    private List<String> options; // Варианты ответов

}
