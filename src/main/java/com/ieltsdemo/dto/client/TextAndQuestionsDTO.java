package com.ieltsdemo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class TextAndQuestionsDTO {
    private String title;
    private String introduction;
    private String content;
    private List<QuestionDTO> questions;

}
