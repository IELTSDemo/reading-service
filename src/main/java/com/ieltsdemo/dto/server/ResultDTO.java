package com.ieltsdemo.dto.server;

import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Результат тестирования")
public class ResultDTO {
    @Schema(description = "Идентификатор теста")
    private String testId;

    @Schema(description = "Идентификатор текста")
    private String textId;

    @Schema(description = "Тип секции")
    private SectionType sectionType;

    @Schema(description = "Тип экзамена")
    private ExamType examType;

    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Количество правильных ответов")
    private int correctAnswers;
}
