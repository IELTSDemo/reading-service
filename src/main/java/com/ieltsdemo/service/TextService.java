package com.ieltsdemo.service;

import com.ieltsdemo.dto.client.TextAndQuestionsDTO;
import com.ieltsdemo.dto.client.TextDTO;
import com.ieltsdemo.util.SectionType;

import java.util.List;

public interface TextService {
    List<TextDTO> findTextByTestIdAndSection(String testId, SectionType section);
    TextAndQuestionsDTO getTextAndQuestions(String textId);

}
