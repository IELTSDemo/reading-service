package com.ieltsdemo.repository;

import com.ieltsdemo.model.Test;
import com.ieltsdemo.util.ExamType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository  extends MongoRepository<Test, String> {
    void deleteTestById(String id);
    List<Test> findTestByExamType(ExamType examType);
    Optional<Test> findByNameAndExamType(String name, ExamType examType);


    List<Test> findTestById(String id);
}
