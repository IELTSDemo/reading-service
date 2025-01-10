package com.ieltsdemo.service.impl;

import com.ieltsdemo.model.Result;
import com.ieltsdemo.repository.ResultRepository;
import com.ieltsdemo.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }
}
