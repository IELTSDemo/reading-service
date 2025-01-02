package com.ieltsdemo.repository;

import com.ieltsdemo.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
