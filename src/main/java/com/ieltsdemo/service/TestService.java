package com.ieltsdemo.service;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.model.Test;
import com.ieltsdemo.util.ExamType;

import java.util.List;

public interface TestService {
    List<TestDTO> getTestsByExamTypeAndEmail(String email, ExamType examType); // General или Academic

    List<TestDTO> getTestsByExamType(ExamType examType);

    Test createTest(CreateTestDTO createTestDTO);
}
