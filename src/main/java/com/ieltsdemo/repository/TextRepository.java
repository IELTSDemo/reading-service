package com.ieltsdemo.repository;

import com.ieltsdemo.model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TextRepository extends MongoRepository<Text, String> {
    List<Text> findByTitleContainingIgnoreCase(String title);
}
