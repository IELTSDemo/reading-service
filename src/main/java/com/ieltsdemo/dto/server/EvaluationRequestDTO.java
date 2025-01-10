package com.ieltsdemo.dto.server;

import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import lombok.Data;

import java.util.List;

@Data
public class EvaluationRequestDTO {
    private List<AnswerSubmissionDTO> answers;
    private ResultDTO result;
}
