package com.ieltsdemo.repository;

import com.ieltsdemo.model.Test;
import com.ieltsdemo.util.ExamType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository  extends MongoRepository<Test, String> {
    List<Test> findTestByExamType(ExamType examType);

    List<Test> findTestById(String id);
}
