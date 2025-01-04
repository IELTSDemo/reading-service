package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.EvaluationDetailDTO;
import com.ieltsdemo.dto.client.EvaluationResultDTO;
import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.service.EvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final QuestionRepository questionRepository;

    public EvaluationServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public EvaluationResultDTO evaluateAnswers(List<AnswerSubmissionDTO> answers) {
        AtomicInteger score = new AtomicInteger();

        List<EvaluationDetailDTO> evaluationDetails = answers.stream().map(answer -> {
            Question question = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found: " + answer.getQuestionId()));

            boolean isCorrect = answer.getUserAnswer().equalsIgnoreCase(question.getCorrectAnswer());

            if (isCorrect) {
                score.getAndIncrement();
            }

            return new EvaluationDetailDTO(
                    question.getNum(),
                    question.getQuestion(),
                    answer.getUserAnswer(),
                    isCorrect,
                    question.getCorrectAnswer(),
                    question.getExplanation()
            );
        }).collect(Collectors.toList());

        return new EvaluationResultDTO(score, evaluationDetails);
    }
}
