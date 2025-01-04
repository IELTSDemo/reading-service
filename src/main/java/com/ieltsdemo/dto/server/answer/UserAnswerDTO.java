package com.ieltsdemo.dto.server.answer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAnswerDTO {
    private String questionId;
    private String answer; // Ответ пользователя
}
