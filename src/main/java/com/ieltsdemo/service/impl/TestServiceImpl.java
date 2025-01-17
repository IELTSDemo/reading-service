package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.dto.server.CreateTestDTO;
import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.Test;
import com.ieltsdemo.repository.ResultRepository;
import com.ieltsdemo.repository.TestRepository;
import com.ieltsdemo.repository.UserRepository;
import com.ieltsdemo.repository.TextRepository;  // Импорт репозитория текстов
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final TextRepository textRepository; // Добавляем TextRepository

    public TestServiceImpl(TestRepository testRepository,
                           ResultRepository resultRepository,
                           UserRepository userRepository,
                           TextRepository textRepository) {  // Обновлённый конструктор
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.textRepository = textRepository;
    }

    @Override
    public List<TestDTO> getTestsByExamTypeAndEmail(String email, ExamType examType) {
        return testRepository.findTestByExamType(examType)
                .stream()
                .map(test -> {
                    // Получаем результаты пользователя по ID теста
                    ArrayList<Result> result = resultRepository.findResultsByEmailAndTestIdAndDeletedFalse(email, test.getId());

                    // Заполняем количество текстов по секциям для данного теста
                    HashMap<String, Integer> textsInSections = new HashMap<>();
                    textsInSections.put("SECTION_ONE", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_ONE));
                    textsInSections.put("SECTION_TWO", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_TWO));
                    textsInSections.put("SECTION_THREE", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_THREE));

                    // Возвращаем DTO с информацией о тесте и результатах
                    return new TestDTO(test.getId(), test.getName(), textsInSections, result);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDTO> getTestsByExamType(ExamType examType) {
        return testRepository.findTestByExamType(examType)
                .stream()
                .map(test -> {
                    ArrayList<Result> result = resultRepository.findResultsByTestId(test.getId());

                    // Заполняем количество текстов по секциям для данного теста
                    HashMap<String, Integer> textsInSections = new HashMap<>();
                    textsInSections.put("SECTION_ONE", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_ONE));
                    textsInSections.put("SECTION_TWO", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_TWO));
                    textsInSections.put("SECTION_THREE", textRepository.countByTestIdAndSection(test.getId(), SectionType.SECTION_THREE));

                    return new TestDTO(test.getId(), test.getName(), textsInSections, result);
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
