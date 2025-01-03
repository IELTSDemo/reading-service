package com.ieltsdemo.service;

import com.ieltsdemo.dto.SectionDTO;
import com.ieltsdemo.util.ExamType;

import java.util.List;

public interface SectionService {
    List<SectionDTO> getSectionsByTestNameAndExamType(String testName, ExamType examType);
}
