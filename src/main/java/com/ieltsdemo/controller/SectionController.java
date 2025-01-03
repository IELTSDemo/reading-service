package com.ieltsdemo.controller;

import com.ieltsdemo.dto.SectionDTO;
import com.ieltsdemo.service.SectionService;
import com.ieltsdemo.util.ExamType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public List<SectionDTO> getStagesForTest(
            @RequestParam String testName,
            @RequestParam ExamType examType
    ) {
        return sectionService.getSectionsByTestNameAndExamType(testName, examType);
    }
}

