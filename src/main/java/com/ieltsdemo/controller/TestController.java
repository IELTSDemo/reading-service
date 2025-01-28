package com.ieltsdemo.controller;

import com.ieltsdemo.dto.client.TestDTO;
import com.ieltsdemo.service.TestService;
import com.ieltsdemo.util.ExamType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/", produces = "application/json;charset=UTF-8")
    public List<TestDTO> getTests(@RequestParam String email, @RequestParam ExamType examType) {
        return testService.getTestsByExamTypeAndEmail(email, examType);
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public List<TestDTO> getAllTests(@RequestParam ExamType examType) {
        return testService.getTestsByExamType(examType);
    }

}
