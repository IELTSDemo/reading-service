package com.ieltsdemo.dto.client;

import com.ieltsdemo.dto.server.ResultDTO;
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
    private ResultDTO result;

}
