package com.ieltsdemo.dto.client;

import com.ieltsdemo.dto.server.ResultDTO;
import com.ieltsdemo.model.Result;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class EvaluationResultDTO {
    private AtomicInteger score; // Итоговый балл
    private List<EvaluationDetailDTO> details; // Детали проверки
}
