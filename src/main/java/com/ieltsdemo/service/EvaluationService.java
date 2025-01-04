package com.ieltsdemo.service;

import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.dto.client.EvaluationResultDTO;

import java.util.List;

public interface EvaluationService {
    EvaluationResultDTO evaluateAnswers(List<AnswerSubmissionDTO> answers);
}
