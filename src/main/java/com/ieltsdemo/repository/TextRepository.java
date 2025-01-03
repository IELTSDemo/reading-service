package com.ieltsdemo.repository;

import com.ieltsdemo.model.Text;
import com.ieltsdemo.util.ExamType;
import com.ieltsdemo.util.SectionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text, String> {
    List<Text> findTextByExamType(ExamType examType);
    List<Text> findTextByTestNameAndExamType(String testName, ExamType examType);
    List<Text> findTextBySectionAndExamType(SectionType section, ExamType examType);
}
