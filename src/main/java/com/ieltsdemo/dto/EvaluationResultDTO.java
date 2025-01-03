package com.ieltsdemo.dto;

import java.util.List;

public class EvaluationResultDTO {
    private int score; // Итоговый балл
    private List<EvaluationDetailDTO> details; // Детали проверки

    public EvaluationResultDTO(int score, List<EvaluationDetailDTO> details) {
        this.score = score;
        this.details = details;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<EvaluationDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<EvaluationDetailDTO> details) {
        this.details = details;
    }
}
