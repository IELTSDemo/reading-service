package com.ieltsdemo.dto.server;

import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import lombok.Data;

@Data
public class ResultDTO {
    private String testId;
    private String textId;
    private SectionType sectionType;
    private ExamType examType;
    private String email;
    private int correctAnswers;
}
