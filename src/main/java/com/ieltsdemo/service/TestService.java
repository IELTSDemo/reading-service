package com.ieltsdemo.service;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.model.Test;
import com.ieltsdemo.util.ExamType;

import java.util.List;

public interface TestService {
    List<TestDTO> getTestsByExamType(ExamType examType); // General или Academic
    Test createTest(CreateTestDTO createTestDTO);
}
