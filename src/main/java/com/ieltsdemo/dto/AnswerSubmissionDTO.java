package com.ieltsdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerSubmissionDTO {
    private String textId;
    private List<UserAnswerDTO> answers;
}
