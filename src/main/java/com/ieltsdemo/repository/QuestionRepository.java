package com.ieltsdemo.repository;

import com.ieltsdemo.model.question.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByRelatedTextId(String relatedTextId);

    void deleteQuestionByRelatedTextId(String textId);
}
