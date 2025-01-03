package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.SectionDTO;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.SectionService;
import com.ieltsdemo.util.ExamType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {

    private final TextRepository textRepository;

    public SectionServiceImpl(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public List<SectionDTO> getSectionsByTestNameAndExamType(String testName, ExamType examType) {
        return textRepository.findTextByTestNameAndExamType(testName, examType)
                .stream()
                .map(text -> new SectionDTO(text.getId(), text.getSection()))
                .distinct()
                .collect(Collectors.toList());
    }
}

