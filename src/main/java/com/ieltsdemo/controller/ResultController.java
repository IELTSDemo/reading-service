package com.ieltsdemo.controller;

import com.ieltsdemo.dto.client.DeleteResultDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.service.ResultService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }

    @PostMapping("/reset")
    public void deleteResult(@RequestBody DeleteResultDTO deleteResult) {
        resultService.deleteResult(deleteResult);
    }
}
