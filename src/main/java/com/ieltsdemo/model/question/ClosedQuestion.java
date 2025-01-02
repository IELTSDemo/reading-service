package com.ieltsdemo.model.question;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class ClosedQuestion extends Question {
    private List<String> options;

}
