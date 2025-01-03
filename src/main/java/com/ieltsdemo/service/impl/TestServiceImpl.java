package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.TestDTO;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.util.ExamType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private final TextRepository textRepository;

    public TestServiceImpl(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public List<TestDTO> getTestsByExamType(ExamType examType) {
        return textRepository.findTextByExamType(examType)
                .stream()
                .map(text -> new TestDTO(text.getId(), text.getTestName()))
                .distinct()
                .collect(Collectors.toList());
    }
}
