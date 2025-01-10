package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.Test;
import com.ieltsdemo.model.User;
import com.ieltsdemo.repository.ResultRepository;
import com.ieltsdemo.repository.TestRepository;
import com.ieltsdemo.repository.UserRepository;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.util.ExamType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    public TestServiceImpl(TestRepository testRepository, ResultRepository resultRepository, UserRepository userRepository) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TestDTO> getTestsByExamTypeAndEmail(String email, ExamType examType) {
        return testRepository.findTestByExamType(examType)
                .stream()
                .map(test -> {
                    // Получаем результат пользователя по ID теста
                    ArrayList<Result> result = resultRepository.findResultsByEmailAndTestIdAndDeletedFalse(email, test.getId()); // Если результат не найден, устанавливаем 0

                    // Возвращаем DTO с информацией о тесте и результатах
                    return new TestDTO(test.getId(), test.getName(), result);
                })
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
