package com.ieltsdemo.service;

import com.ieltsdemo.dto.TestDTO;
import com.ieltsdemo.util.ExamType;

import java.util.List;

public interface TestService {
    List<TestDTO> getTestsByExamType(ExamType examType); // General или Academic
}
