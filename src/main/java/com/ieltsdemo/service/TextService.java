package com.ieltsdemo.service;

import com.ieltsdemo.dto.TextDTO;
import com.ieltsdemo.dto.TextWithQuestionsDTO;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;

import java.util.List;

public interface TextService {
    List<TextDTO> getTextsBySectionAndExamType(SectionType section, ExamType examType);
    TextWithQuestionsDTO getTextAndQuestions(String textId);
}
