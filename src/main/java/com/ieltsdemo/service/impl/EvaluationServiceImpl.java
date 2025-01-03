package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.AnswerSubmissionDTO;
import com.ieltsdemo.dto.EvaluationDetailDTO;
import com.ieltsdemo.dto.EvaluationResultDTO;
import com.ieltsdemo.dto.UserAnswerDTO;
import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.service.EvaluationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final QuestionRepository questionRepository;

    public EvaluationServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public EvaluationResultDTO evaluateAnswers(AnswerSubmissionDTO submissionDTO) {
        List<EvaluationDetailDTO> details = new ArrayList<>();
        int score = 0;

        for (UserAnswerDTO userAnswer : submissionDTO.getAnswers()) {
            Question question = questionRepository.findById(userAnswer.getQuestionId()).orElse(null);
            if (question == null) continue;

            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(userAnswer.getAnswer());
            if (isCorrect) score++;

            details.add(new EvaluationDetailDTO(
                    question.getNum(),
                    question.getText(),
                    userAnswer.getAnswer(),
                    isCorrect,
                    question.getCorrectAnswer()
            ));
        }

        return new EvaluationResultDTO(score, details);
    }
}
