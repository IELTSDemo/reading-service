package com.ieltsdemo.dto.client;

import com.ieltsdemo.model.Result;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class TestDTO {
    private String id; // Название теста, например, "IELTS Test 1"
    private String name; // Название теста, например, "IELTS Test 1"
    private HashMap<String, Integer> textInSections;
    private ArrayList<Result> result;
}
