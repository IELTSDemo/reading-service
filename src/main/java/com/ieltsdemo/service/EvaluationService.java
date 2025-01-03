package com.ieltsdemo.service;

import com.ieltsdemo.dto.AnswerSubmissionDTO;
import com.ieltsdemo.dto.EvaluationResultDTO;

public interface EvaluationService {
    EvaluationResultDTO evaluateAnswers(AnswerSubmissionDTO submissionDTO);
}
