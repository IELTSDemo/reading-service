package com.ieltsdemo.service;

import com.ieltsdemo.dto.client.DeleteAllResultDTO;
import com.ieltsdemo.dto.client.DeleteResultDTO;
import com.ieltsdemo.model.Result;
import java.util.List;

public interface ResultService {
    List<Result> getAllResults();
    Result createResult(Result result);

    void deleteResult(DeleteResultDTO deleteResultDTO);
    void deleteByTestIdAndEmail(DeleteAllResultDTO deleteAllResultDTO);
}
