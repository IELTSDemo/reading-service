package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.EvaluationDetailDTO;
import com.ieltsdemo.dto.client.EvaluationResultDTO;
import com.ieltsdemo.dto.server.answer.AnswerSubmissionDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.service.EvaluationService;
import com.ieltsdemo.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final QuestionRepository questionRepository;
    private final ResultService resultService;

    public EvaluationServiceImpl(QuestionRepository questionRepository, ResultService resultService) {
        this.questionRepository = questionRepository;
        this.resultService = resultService;
    }

    @Override
    public EvaluationResultDTO evaluateAnswers(List<AnswerSubmissionDTO> answers, Result result) {
        AtomicInteger score = new AtomicInteger();

        List<EvaluationDetailDTO> evaluationDetails = answers.stream().map(answer -> {
            // Получение вопроса из базы
            Question question = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found: " + answer.getQuestionId()));

            // Проверка ответа
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

        // Заполнение объекта Result для сохранения в таблицу
        result.setCorrectAnswers(score.get());
        result.setDeleted(false); // Устанавливаем isDeleted в false
        resultService.createResult(result); // Сохранение результата в базу

        return new EvaluationResultDTO(score, evaluationDetails);
    }

}
