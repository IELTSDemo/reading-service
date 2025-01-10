package com.ieltsdemo.service;

import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.dto.client.EvaluationResultDTO;
import com.ieltsdemo.model.Result;

import java.util.List;

public interface EvaluationService {
    EvaluationResultDTO evaluateAnswers(List<AnswerSubmissionDTO> answers, Result result);
}
