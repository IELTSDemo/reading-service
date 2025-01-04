package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.model.Test;
import com.ieltsdemo.repository.TestRepository;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.util.ExamType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public List<TestDTO> getTestsByExamType(ExamType examType) {
        return testRepository.findTestByExamType(examType)
                .stream()
                .map(Test::getId)
                .map(TestDTO::new) // Создаем DTO только с testId
                .collect(Collectors.toList());
    }

    @Override
    public Test createTest(CreateTestDTO createTestDTO) {
        Test test = new Test();
        test.setName(createTestDTO.getName());
        test.setExamType(createTestDTO.getExamType());
        testRepository.save(test);
        return test;
    }
}
