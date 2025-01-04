package com.ieltsdemo.dto.server;

import com.ieltsdemo.util.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTestDTO {
    private String name; // Name of the test, e.g., "IELTS Test 1"
    private ExamType examType; // Type of the test, e.g., GENERAL or ACADEMIC
}
