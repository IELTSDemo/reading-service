package com.ieltsdemo.dto.server.answer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerSubmissionDTO {
    private String questionId; // ID вопроса
    private String userAnswer; // Ответ пользователя
}
