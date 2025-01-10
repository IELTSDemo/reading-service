package com.ieltsdemo.service;

import com.ieltsdemo.model.Result;
import java.util.List;

public interface ResultService {
    List<Result> getAllResults();
    Result createResult(Result result);
}
