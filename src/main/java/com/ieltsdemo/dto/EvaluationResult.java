package com.ieltsdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EvaluationResult {
    private int score; // Итоговый балл
    private List<EvaluationDetail> details; // Детали проверки
}
